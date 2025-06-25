package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Animais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalRepository extends JpaRepository<Animais, Integer> {
}
