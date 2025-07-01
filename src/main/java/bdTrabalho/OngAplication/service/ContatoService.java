package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.dto.ContatoDTO;
import bdTrabalho.OngAplication.model.Contatos;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.repository.ContatoRepository;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import bdTrabalho.OngAplication.validator.ContatoValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ContatoService {

    private final ContatoRepository contatoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ContatoValidator contatoValidator;

    @Transactional
    public Contatos salvarContato(ContatoDTO dto) {
        // 1. Valida regras de negócio
        contatoValidator.validarNovoContato(dto);

        // 2. Busca o usuário que será o "dono" deste contato
        Usuarios usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + dto.usuarioId() + " não encontrado."));

        // 3. Mapeia o DTO para a Entidade
        Contatos novoContato = new Contatos();
        novoContato.setEmail(dto.email());
        novoContato.setCelular(dto.celular());
        novoContato.setUsuario(usuario); // Associa a entidade de usuário completa

        // 4. Salva no banco de dados
        return contatoRepository.save(novoContato);
    }
}