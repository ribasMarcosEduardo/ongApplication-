package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.Model.Doencas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoencaRepository extends JpaRepository<Doencas, Integer> {
}

