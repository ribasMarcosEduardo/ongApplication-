package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.repository.AnimalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {

    private final AnimalRepository repository;

    public Animais saveAnimal(Animais animal){
        return repository.save(animal);
    }

    //public List<Animais> findAll(){
     //   return repository.findAll();
    //}

    public List<Animais> findAll() {
        return repository.findAllByOrderByNomeAsc();
    }

    public long countTotal() {
        return repository.count();
    }

    public long countDisponiveis() {
        return repository.countBySituacao('D');
    }

    public Optional<Animais> findById(int id) {
        return repository.findById(id);
    }

    public void deletarPorId(int id) {
        repository.deleteById(id);

    }

    public List<Animais> findAllOrderedByName() {
        return repository.findAllByOrderByNomeAsc();
    }



}
