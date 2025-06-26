package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.dto.CampanhaDTO;
import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.Campanhas;
import bdTrabalho.OngAplication.repository.CampanhaRepository;
import bdTrabalho.OngAplication.validator.CampanhaValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CampanhaService {

    private final CampanhaRepository repository;
    private final CampanhaValidator validator;

    @Transactional
    public Campanhas saveCampanha(CampanhaDTO campanhaDTO){
        validator.validarCampanha(campanhaDTO);

        Campanhas campanha = campanhaDTO.mapearCampanha();
        return repository.save(campanha);
    }

}
