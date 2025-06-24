package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.Model.Doencas;
import bdTrabalho.OngAplication.repository.DoencaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DoencaService {

    private final DoencaRepository repository;

    public Doencas saveDoenca(Doencas doencas) {
        return repository.save(doencas);
    }

}
