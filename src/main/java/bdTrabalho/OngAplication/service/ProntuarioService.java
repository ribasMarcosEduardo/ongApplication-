package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.Doencas;
import bdTrabalho.OngAplication.model.Prontuarios;
import bdTrabalho.OngAplication.model.Vacinas;
import bdTrabalho.OngAplication.dto.ProntuarioDTO;
import bdTrabalho.OngAplication.repository.AnimalRepository;
import bdTrabalho.OngAplication.repository.DoencaRepository;
import bdTrabalho.OngAplication.repository.ProntuarioRepository;
import bdTrabalho.OngAplication.repository.VacinaRepository;
import bdTrabalho.OngAplication.validator.ProntuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;
    private final VacinaRepository vacinaRepository;
    private final DoencaRepository doencaRepository;
    private final AnimalRepository animalRepository;
    private final ProntuarioValidator prontuarioValidator;

    @Transactional
    public void saveProntuario(ProntuarioDTO dto) {
        Animais animal = animalRepository.findById(dto.animalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com o ID: " + dto.animalId()));

        Prontuarios prontuario;

        if (dto.id() == 0 || dto.id() == 0) {
            prontuarioValidator.validateNewProntuario(dto);
            prontuario = new Prontuarios();
        } else {
            prontuario = prontuarioRepository.findById(dto.id())
                    .orElseThrow(() -> new RuntimeException("Prontuário não encontrado para o ID: " + dto.id()));
        }

        prontuario.setObservacoesGerais(dto.observacoesGerais());
        prontuario.setAlergias(dto.alergias());
        prontuario.setDeficiencia(dto.deficiencia());
        if (dto.castrado() != null) {
            prontuario.setCastrado(dto.castrado());
        }
        prontuario.setAnimal(animal);

        prontuario.getDoencas().clear();
        if (dto.doencaIds() != null && !dto.doencaIds().isEmpty()) {
            Set<Doencas> novasDoencas = new HashSet<>(doencaRepository.findAllById(dto.doencaIds()));
            prontuario.getDoencas().addAll(novasDoencas);
        }

        prontuario.getVacinas().clear();
        if (dto.vacinaIds() != null && !dto.vacinaIds().isEmpty()) {
            Set<Vacinas> novasVacinas = new HashSet<>(vacinaRepository.findAllById(dto.vacinaIds()));
            prontuario.getVacinas().addAll(novasVacinas);
        }

        prontuarioRepository.save(prontuario);
    }

    @Transactional(readOnly = true)
    public Optional<Prontuarios> findByAnimalId(Integer animalId) {
        return prontuarioRepository.findByAnimalId(animalId);
    }
}
