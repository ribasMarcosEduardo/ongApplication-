package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.Model.Animais;
import bdTrabalho.OngAplication.Model.Vacinas;
import bdTrabalho.OngAplication.repository.VacinaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class VacinaService {

    private final VacinaRepository repository;

    public Vacinas saveVacina(Vacinas vacina) {
        return repository.save(vacina);
    }

}
