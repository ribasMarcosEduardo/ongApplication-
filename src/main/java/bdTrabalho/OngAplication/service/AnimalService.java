package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.dto.AnimalDetalheDTO;
import bdTrabalho.OngAplication.dto.DoencaDTO;
import bdTrabalho.OngAplication.dto.ProntuarioDetalheDTO;
import bdTrabalho.OngAplication.dto.VacinaDTO;
import bdTrabalho.OngAplication.dto.AnimalDetalheDTO;
import bdTrabalho.OngAplication.dto.ProntuarioDetalheDTO;
import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;
import bdTrabalho.OngAplication.model.Prontuarios;
import bdTrabalho.OngAplication.repository.AnimalRepository;
import bdTrabalho.OngAplication.validator.AnimalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalValidator validator;
    private final AnimalRepository repository;

    public Animais saveAnimal(Animais animal) {
        return repository.save(animal);
    }

    public List<Animais> findAll() {
        return repository.findAllByOrderByNomeAsc();
    }

    public long countTotal() {
        return repository.count();
    }

    public long countDisponiveis() {
        return repository.countBySituacao('D');
    }

    public Optional<Animais> findById(int id) {
        return repository.findById(id);
    }

    public void deletarPorId(int id) {
        Animais animal = repository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal com ID " + id + " não encontrado."));

        validator.validateForDeletion(animal);
        repository.deleteById(id);

    }

    public List<Animais> findAllOrderedByName() {
        return repository.findAllByOrderByNomeAsc();
    }

    // Método para a busca com filtros na vitrine
    public List<Animais> findAnimaisDisponiveisComFiltros(TipoAnimal tipo, String cor) {
        return repository.findAnimaisDisponiveisComFiltros(tipo, cor);
    }


    public AnimalDetalheDTO getDetalhesAnimal(int id) {
        // 1. Busca o animal no banco ou lança um erro se não existir.
        Animais animal = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com o ID: " + id));

        ProntuarioDetalheDTO prontuarioDTO = null;

        // 2. Verifica se existe um prontuário para este animal.
        if (animal.getProntuario() != null) {
            Prontuarios prontuario = animal.getProntuario();

            // 3. Converte a lista de entidades Doenca para uma lista de DoencaDTO.
            List<DoencaDTO> doencasDTO = prontuario.getDoencas() != null ?
                    prontuario.getDoencas().stream()
                            // CORREÇÃO APLICADA AQUI: Adicionando o d.getId()
                            .map(d -> new DoencaDTO(d.getId(), d.getCid(), d.getNome()))
                            .collect(Collectors.toList()) : Collections.emptyList();

            // 4. Converte a lista de entidades Vacina para uma lista de VacinaDTO.
            List<VacinaDTO> vacinasDTO = prontuario.getVacinas() != null ?
                    prontuario.getVacinas().stream()
                            // Assumindo que seu VacinaDTO também tem um ID, se não, ajuste conforme necessário
                            .map(v -> new VacinaDTO(v.getId(), v.getNome(), v.getCodigo()))
                            .collect(Collectors.toList()) : Collections.emptyList();

            // 5. Monta o DTO do prontuário com todas as informações.
            prontuarioDTO = new ProntuarioDetalheDTO(
                    prontuario.getCastrado(),
                    prontuario.getAlergias(),
                    prontuario.getDeficiencia(),
                    prontuario.getObservacoesGerais(),
                    doencasDTO,
                    vacinasDTO
            );
        }

        // 6. Finalmente, monta e retorna o DTO principal com todos os dados do animal.
        return new AnimalDetalheDTO(
                animal.getId(),
                animal.getNome(),
                animal.getDescricao(),
                animal.getRaca(),
                animal.getIdade(),
                animal.getTipo(),
                animal.getFoto(),
                animal.getPeso(),
                animal.getSexo(),
                animal.getPorte(),
                animal.getDataChegada(),
                animal.getHistoria(),
                prontuarioDTO
        );
    }
    @Transactional(readOnly = true)
    public List<Animais> findAllFilteredBySituacao(String situacao) {

        if (situacao == null || situacao.trim().isEmpty()) {

            return repository.findAllByOrderByNomeAsc();
        } else {

            char situacaoChar = situacao.charAt(0);

            return repository.findBySituacaoOrderByNomeAsc(situacaoChar);
        }
    }
}
