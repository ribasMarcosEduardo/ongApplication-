package bdTrabalho.OngAplication.validator;

import bdTrabalho.OngAplication.dto.EnderecoDTO;
import bdTrabalho.OngAplication.repository.EnderecoRepository;
import org.springframework.stereotype.Component;

@Component
public class EnderecoValidator {

    private final EnderecoRepository enderecoRepository;

    public EnderecoValidator(EnderecoRepository enderecoRepository) {
        this.enderecoRepository = enderecoRepository;
    }

    public void validarNovoEndereco(EnderecoDTO dto) {
        // Verifica se já existe um endereço com os mesmos dados para o mesmo usuário
        boolean enderecoJaExiste = enderecoRepository.existsByCepAndRuaAndNumeroAndUsuarioId(
                dto.cep(),
                dto.rua(),
                dto.numero(),
                dto.usuarioId()
        );

        if (enderecoJaExiste) {
            throw new IllegalArgumentException("Este endereço já está cadastrado para o usuário selecionado.");
        }
    }
}
