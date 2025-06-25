package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.dto.UsuarioDTO;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public Usuarios criarUsuario(UsuarioDTO usuarioDTO) {
        // Validação de negócio (ex: verificar CPF duplicado)
        if (usuarioRepository.existsByCpf(usuarioDTO.getCpf())) {
            throw new IllegalArgumentException("CPF já cadastrado no sistema.");
        }

        Usuarios novoUsuario = new Usuarios();
        novoUsuario.setNome(usuarioDTO.getNome());
        novoUsuario.setCpf(usuarioDTO.getCpf());
        novoUsuario.setDataNascimento(usuarioDTO.getDataNascimento());
        novoUsuario.setSenha(usuarioDTO.getSenha());
        novoUsuario.setGenero(usuarioDTO.getGenero());
        novoUsuario.setTipo(usuarioDTO.getTipo());
        novoUsuario.setFoto(usuarioDTO.getFoto());
        novoUsuario.setDataCadastro(LocalDateTime.now());


        return usuarioRepository.save(novoUsuario);
    }
}