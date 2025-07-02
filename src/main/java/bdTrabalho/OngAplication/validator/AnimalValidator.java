package bdTrabalho.OngAplication.validator;

import bdTrabalho.OngAplication.exeption.MensagemPadrao;
import bdTrabalho.OngAplication.model.Animais;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AnimalValidator {

    public void validateForDeletion(Animais animal) {

        if (animal.getSituacao() != 'D') {
            throw new MensagemPadrao("Não é possível excluir este animal, pois ele está em processo de adoção ou já foi adotado. Situação: " + animal.getSituacao());
        }
    }
}
