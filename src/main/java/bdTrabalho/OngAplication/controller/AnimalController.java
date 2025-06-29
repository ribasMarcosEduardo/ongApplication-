package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.AnimalDTO;
import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.Campanhas;
import bdTrabalho.OngAplication.model.EMUN.PorteAnimal;
import bdTrabalho.OngAplication.model.EMUN.TipoAnimal;
import bdTrabalho.OngAplication.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/cadastroAnimal")
    public String cadastroAnimail(Model model) {

        model.addAttribute("animalDTO", new AnimalDTO(
                0, null, null, null, null, null, null, null, null,
                ' ', null, null, null, null, null
        ));

        model.addAttribute("tiposDeAnimal", TipoAnimal.values());
        model.addAttribute("portesDeAnimal", PorteAnimal.values());

        return "Cadastros/animalCadastro";
        // http://localhost:8080/animal/cadastroAnimal
    }


    @PostMapping("/salvarAnimal")
    public String saveAnimal(@ModelAttribute("animalDTO") AnimalDTO animalDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {

            return "Cadastros/animalCadastro";
        }

        try {

            Animais animal = animalDTO.mapearAnimal();
            animal.setSituacao('D');


            animalService.saveAnimal(animal);

            redirectAttributes.addFlashAttribute("Sucesso", "Animal salvo com sucesso!");
            return "redirect:/animal/cadastroAnimal";

        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("Erro", "Erro ao salvar animal: " + e.getMessage());
            return "redirect:/animal/cadastroAnimal";
        }
        //MUITA COISA AINDA VAI SER APRIMORADA
    }

    @GetMapping("/listaAnimal")
    public String listaAnimal(Model model) {

        List<Animais> todosOsAnimais = animalService.findAll();

        model.addAttribute("listaAnimais", todosOsAnimais);
        return "listagens/listarAnimais"; // http://localhost:8080/animal/listaAnimal
    }

    @GetMapping("/editarAnimal")
    public String editarAnimal(@RequestParam("id") int id, Model model) {

        Animais animalExistente = animalService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrada para o ID: " + id));

        AnimalDTO animalDTO = AnimalDTO.fromEntity(animalExistente);

        model.addAttribute("animalDTO", animalDTO);
        model.addAttribute("tiposDeAnimal", TipoAnimal.values());
        model.addAttribute("portesDeAnimal", PorteAnimal.values());

        return "cadastros/animalCadastro";

    }

    @GetMapping("/excluir")
    public String deletarAnimal(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        try {
            animalService.deletarPorId(id);
            redirectAttributes.addFlashAttribute("Sucesso", "Animal excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("Erro", "Erro ao excluir animal. Verifique se ele não possui prontuários ou outros registros associados.");
            e.printStackTrace();
        }

        return "redirect:/animal/listaAnimal";
    }
}