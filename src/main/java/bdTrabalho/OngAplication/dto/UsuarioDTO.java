package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.Model.EMUN.Genero;
import br.com.caelum.stella.bean.validation.CPF;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

@Data
public class UsuarioDTO {

    @NotBlank(message = "O nome não pode estar em branco.")
    @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.")
    private String nome;

    @NotBlank(message = "O CPF não pode estar em branco.")
    @CPF(message = "O CPF fornecido é inválido.") //Notação da biblioteca Stella
    private String cpf;

    @NotNull(message = "A data de nascimento não pode ser nula.")
    @Past(message = "A data de nascimento deve ser no passado.")
    private LocalDate dataNascimento;

    private Genero genero;  //ENUM estabelecido anres.

    @NotNull(message = "O tipo não pode ser nulo.")
    private char tipo;

    private String foto;    //Opcional

    @NotBlank(message = "A senha não pode estar em branco.")
    @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.")
    private String senha;
}
