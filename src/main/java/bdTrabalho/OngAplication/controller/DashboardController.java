package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final AnimalService animalService;

    @GetMapping("/menuPrincipal")
    public String showDashboard(Model model) {

        long totalDeAnimais = animalService.countTotal();
        long animaisDisponiveis = animalService.countDisponiveis();


        model.addAttribute("totalDeAnimais", totalDeAnimais);
        model.addAttribute("animaisDisponiveis", animaisDisponiveis);

        return "index";
    }
}