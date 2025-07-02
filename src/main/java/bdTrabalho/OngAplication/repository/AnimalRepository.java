package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.ENUM.PorteAnimal;
import bdTrabalho.OngAplication.model.ENUM.Situacao;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnimalRepository extends JpaRepository<Animais, Integer> {

    long countBySituacao(char situacao);

   List<Animais> findAllByOrderByNomeAsc();



    @Query("SELECT a FROM Animais a WHERE " +
            "a.situacao = 'D' AND " +
            "(:tipo IS NULL OR a.tipo = :tipo) AND " +
            "(:cor IS NULL OR a.cor LIKE CONCAT('%', :cor, '%'))")
    List<Animais> findAnimaisDisponiveisComFiltros(
            @Param("tipo") TipoAnimal tipo,
            @Param("cor") String cor
    );

    List<Animais> findBySituacaoOrderByNomeAsc(char situacao);


}

