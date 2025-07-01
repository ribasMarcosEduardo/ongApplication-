package bdTrabalho.OngAplication.service;

import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.ENUM.PorteAnimal;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;
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

    // Método para a busca com filtros na vitrine
    public List<Animais> findAnimaisDisponiveisComFiltros(TipoAnimal tipo, String cor) {
        return repository.findAnimaisDisponiveisComFiltros(tipo, cor);
    }

}
