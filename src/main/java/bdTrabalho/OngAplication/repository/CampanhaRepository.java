package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Campanhas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CampanhaRepository extends JpaRepository<Campanhas, Integer> {


    boolean existsByNomeIgnoreCaseAndIdNot(String nome, int id);

    List<Campanhas> findAllByOrderByNomeAsc();

}
