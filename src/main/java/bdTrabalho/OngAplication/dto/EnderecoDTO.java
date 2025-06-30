package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.Enderecos;
import bdTrabalho.OngAplication.model.ENUM.TipoEndereco;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EnderecoDTO(
        Integer id,

        @NotBlank(message = "O CEP não pode estar em branco.")
        @Pattern(regexp = "[0-9]{8}", message = "O CEP deve conter exatamente 8 números.")
        String cep,

        @NotBlank(message = "A rua não pode estar em branco.")
        String rua,

        @NotBlank(message = "A cidade não pode estar em branco.")
        String cidade,

        @NotBlank(message = "O bairro não pode estar em branco.")
        String bairro,

        @NotBlank(message = "O estado não pode estar em branco.")
        @Size(min = 2, max = 2, message = "O estado deve ser a sigla com 2 caracteres (ex: SC).")
        String estado,

        @NotBlank(message = "O número não pode estar em branco.")
        String numero,

        @NotBlank(message = "O país não pode estar em branco.")
        String pais,

        @NotNull(message = "O tipo de endereço é obrigatório.")
        TipoEndereco tipo,

        //Relação com o ID do usuário
        @NotNull(message = "É obrigatório associar o endereço a um usuário.")
        Integer usuarioId
)
{
    public static EnderecoDTO fromEntity(Enderecos endereco) {
        return new EnderecoDTO(
                endereco.getId(),
                endereco.getCep(),
                endereco.getRua(),
                endereco.getCidade(),
                endereco.getBairro(),
                endereco.getEstado(),
                endereco.getNumero(),
                endereco.getPais(),
                endereco.getTipo(),
                endereco.getUsuario().getId()
        );
    }
}