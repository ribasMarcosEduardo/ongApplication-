package bdTrabalho.OngAplication.validator;

import bdTrabalho.OngAplication.dto.UsuarioDTO;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;

    public void validarNovoUsuario(UsuarioDTO dto) {
        // Verificar se o CPF já existe
        if (usuarioRepository.existsByCpf(dto.cpf())) {
            // Se existir, lança uma exceção que será tratada no Controller
            throw new IllegalArgumentException("O CPF informado já está cadastrado.");
        }
    }
}