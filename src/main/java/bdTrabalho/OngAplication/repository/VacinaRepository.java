package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.Model.Vacinas;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VacinaRepository extends JpaRepository<Vacinas, Integer> {
}
