package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.Contatos;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record ContatoDTO(
        Integer id,

        @NotBlank(message = "O email não pode estar em branco.")
        @Email(message = "O formato do email é inválido.")
        String email,

        @NotBlank(message = "O celular não pode estar em branco.")
        @Pattern(regexp = "[0-9]{11}", message = "O celular deve conter 11 números, incluindo o DDD.")
        String celular,

        @NotNull(message = "É obrigatório associar o contato a um usuário.")
        Integer usuarioId
) {

    public static ContatoDTO fromEntity(Contatos contato) {
        return new ContatoDTO(
                contato.getId(),
                contato.getEmail(),
                contato.getCelular(),
                contato.getUsuario().getId()
        );
    }
}