package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.ENUM.Genero;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.validator.dtoValidatorGroup.OnCreate;
import bdTrabalho.OngAplication.validator.dtoValidatorGroup.OnUpdate;
import org.hibernate.validator.constraints.br.CPF;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;
import java.time.LocalDateTime;

//Transformado em um 'record'
public record UsuarioDTO(

        Integer id,

        @NotBlank(message = "O nome não pode estar em branco.", groups = {OnCreate.class, OnUpdate.class})
        @Size(min = 3, message = "O nome deve ter no mínimo 3 caracteres.", groups = {OnCreate.class, OnUpdate.class})
        String nome,

        @NotBlank(message = "O CPF não pode estar em branco.", groups = {OnCreate.class, OnUpdate.class})
        @CPF(message = "O CPF fornecido é inválido.", groups = {OnCreate.class, OnUpdate.class})
        String cpf,

        @NotNull(message = "A data de nascimento não pode ser nula.", groups = {OnCreate.class, OnUpdate.class})
        @Past(message = "A data de nascimento deve ser no passado.", groups = {OnCreate.class, OnUpdate.class})
        LocalDate dataNascimento,

        Genero genero,

        //@NotNull(message = "O tipo não pode ser nulo.")
        //char tipo,

        String foto,

        @NotBlank(message = "A senha não pode estar em branco.", groups = OnCreate.class)
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres.", groups = OnCreate.class)
        String senha
) {

    // Método para converter DTO em Entidade
    public Usuarios toEntity() {
        Usuarios usuario = new Usuarios();
        usuario.setNome(this.nome);
        usuario.setCpf(this.cpf);
        usuario.setDataNascimento(this.dataNascimento);
        usuario.setSenha(this.senha); // TODO: criptografar a senha no service depois
        usuario.setGenero(this.genero);
        //usuario.setTipo(this.tipo);
        usuario.setFoto(this.foto);
        usuario.setDataCadastro(LocalDateTime.now());
        return usuario;
    }

    // Método para criar DTO a partir de uma Entidade
    public static UsuarioDTO fromEntity(Usuarios usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getCpf(),
                usuario.getDataNascimento(),
                usuario.getGenero(),
                //usuario.getTipo(),
                usuario.getFoto(),
                null
        );
    }
}