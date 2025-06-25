package bdTrabalho.OngAplication.validator;

import bdTrabalho.OngAplication.dto.ProntuarioDTO;
import bdTrabalho.OngAplication.exeption.MensagemPadrao;
import bdTrabalho.OngAplication.repository.ProntuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ProntuarioValidator {

    private final ProntuarioRepository prontuarioRepository;

    public void validateNewProntuario(ProntuarioDTO dto) {

        if (prontuarioRepository.existsByAnimalId(dto.animalId())) {
            throw new MensagemPadrao("Já existe um prontuário cadastrado para este animal.");
        }
    }
}
