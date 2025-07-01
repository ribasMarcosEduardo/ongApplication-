package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.exeption.MensagemPadrao;
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

    @Transactional
    public Usuarios salvar(UsuarioDTO usuarioDTO) {
        if (usuarioDTO.id() == null || usuarioDTO.id() == 0) {
            usuarioValidator.validarNovoUsuario(usuarioDTO);
            Usuarios novoUsuario = usuarioDTO.toEntity();
            novoUsuario.setSenha(usuarioDTO.senha());
            return usuarioRepository.save(novoUsuario);
        } else {
            Usuarios usuarioExistente = usuarioRepository.findById(usuarioDTO.id())
                    .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado para o ID: " + usuarioDTO.id()));

            usuarioExistente.setNome(usuarioDTO.nome());
            usuarioExistente.setCpf(usuarioDTO.cpf());
            usuarioExistente.setDataNascimento(usuarioDTO.dataNascimento());
            usuarioExistente.setGenero(usuarioDTO.genero());
            //usuarioExistente.setTipo(usuarioDTO.tipo());
            usuarioExistente.setFoto(usuarioDTO.foto());

            if (usuarioDTO.senha() != null && !usuarioDTO.senha().isBlank()) {
                usuarioExistente.setSenha(usuarioDTO.senha());
            }

            return usuarioRepository.save(usuarioExistente);
        }
    }

        public List<Usuarios> findAll() {
        return usuarioRepository.findAllByOrderByNomeAsc();
    }

    public Optional<Usuarios> findById(int id) {
        return usuarioRepository.findById(id);
    }

    @Transactional
    public void excluirPorId(Integer id) {
        if (!usuarioRepository.existsById(id)) {
            throw new MensagemPadrao("Usuário com ID " + id + " não encontrado.");
        }
        usuarioRepository.deleteById(id);
    }

}
