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
    public void saveProntuario(ProntuarioDTO prontuarioDTO){

        prontuarioValidator.validateNewProntuario(prontuarioDTO);

        Animais animal = animalRepository.findById(prontuarioDTO.animalId())
                .orElseThrow(() -> new RuntimeException("Animal não encontrado com o ID: " + prontuarioDTO.animalId()));

        Prontuarios prontuario = new Prontuarios();
        prontuario.setObservacoesGerais(prontuarioDTO.observacoesGerais());
        prontuario.setAlergias(prontuarioDTO.alergias());
        prontuario.setDeficiencia(prontuarioDTO.deficiencia());

        if(prontuarioDTO.castrado() != null) {
            prontuario.setCastrado(prontuarioDTO.castrado());
        }

        prontuario.setAnimal(animal);

        if (prontuarioDTO.doencaIds() != null && !prontuarioDTO.doencaIds().isEmpty()) {
            Set<Doencas> doencas = new HashSet<>(doencaRepository.findAllById(prontuarioDTO.doencaIds()));
            prontuario.setDoencas(doencas);
        }

        if(prontuarioDTO.vacinaIds() != null && !prontuarioDTO.vacinaIds().isEmpty()){
            Set<Vacinas> vacinas = new HashSet<>(vacinaRepository.findAllById(prontuarioDTO.vacinaIds()));
            prontuario.setVacinas(vacinas);
        }

        prontuarioRepository.save(prontuario);

    }

}
