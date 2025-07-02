package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;
import bdTrabalho.OngAplication.repository.AnimalRepository;
import bdTrabalho.OngAplication.validator.AnimalValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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
