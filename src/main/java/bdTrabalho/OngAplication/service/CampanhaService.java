package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.dto.CampanhaDTO;
import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.Campanhas;
import bdTrabalho.OngAplication.repository.CampanhaRepository;
import bdTrabalho.OngAplication.validator.CampanhaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository repository;
    private final CampanhaValidator validator;

    @Transactional
    public Campanhas saveCampanha(CampanhaDTO campanhaDTO) {
        validator.validarCampanha(campanhaDTO);

        Campanhas campanha = campanhaDTO.mapearCampanha();
        return repository.save(campanha);

    }

    public List<Campanhas> findAll() {
        return repository.findAllByOrderByNomeAsc();
    }

    public Optional<Campanhas> findById(int id) {
        return repository.findById(id);
    }

    public void deletarPorId(int id) {
        repository.deleteById(id);
    }

}
