package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.AnimalDetalheDTO;
import bdTrabalho.OngAplication.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController // RestController para retornar JSON
@RequestMapping("/api/animal")
@RequiredArgsConstructor
public class AnimalApiController {

    private final AnimalService animalService;

    @GetMapping("/detalhe/{id}")
    public ResponseEntity<AnimalDetalheDTO> getDetalhesDoAnimal(@PathVariable int id) {
        try {
            AnimalDetalheDTO animalDetalhes = animalService.getDetalhesAnimal(id);
            return ResponseEntity.ok(animalDetalhes);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}