package bdTrabalho.OngAplication.repository;

import bdTrabalho.OngAplication.model.Contatos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContatoRepository extends JpaRepository<Contatos, Integer> {

    // Método para verificar se um email já existe no banco,
    // ignorando o próprio contato em caso de edição (idNot)
    boolean existsByEmailAndIdNot(String email, int id);

    // Método para verificação simples de email no cadastro
    boolean existsByEmail(String email);
}