package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.dto.UsuarioDTO;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import bdTrabalho.OngAplication.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final UsuarioValidator usuarioValidator;

    @Transactional
    public Usuarios criarUsuario(UsuarioDTO usuarioDTO) {
        usuarioValidator.validarNovoUsuario(usuarioDTO);
        Usuarios novoUsuario = usuarioDTO.toEntity();
        return usuarioRepository.save(novoUsuario);
    }
}
