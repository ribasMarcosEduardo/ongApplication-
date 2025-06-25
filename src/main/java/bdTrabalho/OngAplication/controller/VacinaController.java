package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.model.Vacinas;
import bdTrabalho.OngAplication.dto.VacinaDTO;
import bdTrabalho.OngAplication.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/vacina")
public class VacinaController {

    private final VacinaService vacinaService;

    @GetMapping("cadastroVacina")
    public String cadastroVacina(Model model) {
        model.addAttribute("vacinaDTO", new VacinaDTO(0, null, null));
        return "Cadastros/vacinaCadastro";
        // http://localhost:8080/vacina/cadastroVacina
    }

    @PostMapping("/salvarVacina")
    public String salvarVacina(@ModelAttribute("vacinaDTO") VacinaDTO vacinaDTO, RedirectAttributes redirectAttributes) {
        try {
            Vacinas vacina = vacinaDTO.mapearVacina();
            vacinaService.saveVacina(vacina);
            redirectAttributes.addFlashAttribute("Sucesso", "Vacina salva com sucesso!");
            return "redirect:/vacina/cadastroVacina";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("Erro", "Erro ao salvar vacina: " + e.getMessage());
            return "redirect:/vacina/cadastroVacina";
        }
    }

}
