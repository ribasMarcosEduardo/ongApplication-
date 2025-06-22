package bdTrabalho.OngAplication.Repository;

import bdTrabalho.OngAplication.Model.Usuarios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface usuarioRepository extends JpaRepository<Usuarios, Integer> {
}
