package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Doencas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoencaRepository extends JpaRepository<Doencas, Integer> {
}

