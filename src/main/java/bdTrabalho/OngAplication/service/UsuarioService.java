package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.dto.UsuarioDTO;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import bdTrabalho.OngAplication.validator.UsuarioValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

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

    public List<Usuarios> findAll() {
        return usuarioRepository.findAllByOrderByNomeAsc();
    }

    public Optional<Usuarios> findById(int id) {
        return usuarioRepository.findById(id);
    }

}
