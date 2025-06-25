package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Prontuarios;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProntuarioRepository extends JpaRepository<Prontuarios, Integer> {

    boolean existsByAnimalId(Integer animalId);
}
