package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Usuarios;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuarios, Integer> {
    boolean existsByCpf(@NotBlank(message = "O CPF não pode estar em branco.") String cpf);
}
