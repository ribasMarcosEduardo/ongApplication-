package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.dto.EnderecoDTO;
import bdTrabalho.OngAplication.model.Enderecos;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.repository.EnderecoRepository;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import bdTrabalho.OngAplication.validator.EnderecoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository enderecoRepository;
    private final UsuarioRepository usuarioRepository;
    private final EnderecoValidator enderecoValidator;

    @Transactional
    public Enderecos salvarEndereco(EnderecoDTO enderecoDTO) {
        enderecoValidator.validarNovoEndereco(enderecoDTO);

        Usuarios usuarioAssociado = usuarioRepository.findById(enderecoDTO.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário com o ID " + enderecoDTO.usuarioId() + " não foi encontrado."));

        Enderecos novoEndereco = new Enderecos();
        novoEndereco.setCep(enderecoDTO.cep());
        novoEndereco.setRua(enderecoDTO.rua());
        novoEndereco.setCidade(enderecoDTO.cidade());
        novoEndereco.setBairro(enderecoDTO.bairro());
        novoEndereco.setEstado(enderecoDTO.estado());
        novoEndereco.setNumero(enderecoDTO.numero());
        novoEndereco.setPais(enderecoDTO.pais());
        novoEndereco.setTipo(enderecoDTO.tipo());
        novoEndereco.setUsuario(usuarioAssociado);

        return enderecoRepository.save(novoEndereco);
    }
}