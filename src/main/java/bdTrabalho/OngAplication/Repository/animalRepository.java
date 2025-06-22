package bdTrabalho.OngAplication.Repository;

import bdTrabalho.OngAplication.Model.Animais;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface animalRepository extends JpaRepository<Animais, Integer> {
}
