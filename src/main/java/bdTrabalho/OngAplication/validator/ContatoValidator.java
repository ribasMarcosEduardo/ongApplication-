package bdTrabalho.OngAplication.validator;

import bdTrabalho.OngAplication.dto.ContatoDTO;
import bdTrabalho.OngAplication.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContatoValidator {

    private final ContatoRepository contatoRepository;

    public void validarNovoContato(ContatoDTO dto) {
        // Não permitir emails duplicados no sistema.
        if (contatoRepository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("O email informado já está cadastrado.");
        }
    }
}