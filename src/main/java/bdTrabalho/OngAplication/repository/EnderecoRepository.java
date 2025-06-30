package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Enderecos;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnderecoRepository extends JpaRepository<Enderecos, Integer> {

    boolean existsByCepAndRuaAndNumeroAndUsuarioId(@NotBlank(message = "O CEP não pode estar em branco.") @Pattern(regexp = "[0-9]{8}", message = "O CEP deve conter exatamente 8 números.") String cep, @NotBlank(message = "A rua não pode estar em branco.") String rua, @NotBlank(message = "O número não pode estar em branco.") String numero, @NotNull(message = "É obrigatório associar o endereço a um usuário.") Integer integer);
}
