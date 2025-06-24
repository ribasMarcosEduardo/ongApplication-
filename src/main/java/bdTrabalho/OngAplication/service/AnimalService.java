package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.Model.Animais;
import bdTrabalho.OngAplication.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository repository;

    public Animais saveAnimal(Animais animal){
        return repository.save(animal);
    }

}
