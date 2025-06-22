package bdTrabalho.OngAplication.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/animal")
public class animalController {

    @GetMapping("/cadastroAnimal")
    public String cadastroAnimail(Model model){
        return "Cadastros/animalCadastro";
        // http://localhost:8080/animal/cadastroAnimal
    }

}