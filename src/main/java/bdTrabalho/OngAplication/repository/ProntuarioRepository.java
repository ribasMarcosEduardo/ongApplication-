package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Prontuarios;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProntuarioRepository extends JpaRepository<Prontuarios, Integer> {

    boolean existsByAnimalId(Integer animalId);

    Optional<Prontuarios> findByAnimalId(int animalId);


}
