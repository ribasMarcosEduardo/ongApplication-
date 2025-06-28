package bdTrabalho.OngAplication.validator;

import bdTrabalho.OngAplication.dto.CampanhaDTO;
import bdTrabalho.OngAplication.exeption.MensagemPadrao;
import bdTrabalho.OngAplication.repository.CampanhaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CampanhaValidator {

    private final CampanhaRepository campanhaRepository;

    public void validarCampanha(CampanhaDTO dto) {

        if (campanhaRepository.existsByNomeIgnoreCaseAndIdNot(dto.nome(), dto.id())) {
            throw new MensagemPadrao("Já existe uma campanha cadastrada com este nome.");
        }

        if (dto.dataInicio() != null && dto.dataTermino() != null) {
            if (dto.dataTermino().isBefore(dto.dataInicio())) {
                throw new MensagemPadrao("A data de término não pode ser anterior à data de início.");
            }
        }

    }

}
