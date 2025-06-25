package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Doencas;
import bdTrabalho.OngAplication.repository.DoencaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DoencaService {

    private final DoencaRepository repository;

    public Doencas saveDoenca(Doencas doencas) {
        return repository.save(doencas);
    }

    public List<Doencas> findAll(){
        return repository.findAll();
    }

}
