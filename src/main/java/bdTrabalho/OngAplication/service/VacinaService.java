package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Vacinas;
import bdTrabalho.OngAplication.repository.VacinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VacinaService {

    private final VacinaRepository repository;

    public Vacinas saveVacina(Vacinas vacina) {
        return repository.save(vacina);
    }

    public List<Vacinas> findAll(){
        return repository.findAll();
    }

}
