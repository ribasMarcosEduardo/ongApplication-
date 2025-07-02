package bdTrabalho.OngAplication.validator;

import bdTrabalho.OngAplication.dto.UsuarioDTO;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.repository.AdocaoRepository;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioValidator {

    private final UsuarioRepository usuarioRepository;
    private final AdocaoRepository adocaoRepository;

    public void validarNovoUsuario(UsuarioDTO dto) {
        if (usuarioRepository.existsByCpf(dto.cpf())) {
            throw new IllegalArgumentException("O CPF informado já está cadastrado.");
        }
    }

    public void validateForDeletion(Usuarios usuario) {

        long adocoesCount = adocaoRepository.countByUsuarioId(usuario.getId());

        if (adocoesCount > 0) {
            throw new IllegalStateException("Não é possível excluir este usuário, pois ele está associado a " + adocoesCount + " registro(s) de adoção.");
        }
    }
}