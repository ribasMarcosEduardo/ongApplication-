package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.dto.AnimalDTO;
import bdTrabalho.OngAplication.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/animal")
public class AnimalController {

    private final AnimalService animalService;

    @GetMapping("/cadastroAnimal")
    public String cadastroAnimail(Model model) {
        model.addAttribute("animalDTO", new AnimalDTO(0, null, null, null, null, null, null, null, null, ' ', null, null, null, null, null));
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


}