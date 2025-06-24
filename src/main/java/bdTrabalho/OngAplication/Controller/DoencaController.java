package bdTrabalho.OngAplication.Controller;

import bdTrabalho.OngAplication.Model.Doencas;
import bdTrabalho.OngAplication.Model.Vacinas;
import bdTrabalho.OngAplication.dto.DoencaDTO;
import bdTrabalho.OngAplication.service.DoencaService;
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
@RequestMapping("/doenca")
public class DoencaController {

    private final DoencaService doencaService;

    @GetMapping("cadastroDoenca")
    public String cadastroDoenca(Model model){
        model.addAttribute("doencaDTO", new DoencaDTO(0,null,null));
        return "Cadastros/doencaCadastro";
        // http://localhost:8080/doenca/cadastroDoenca
    }

    @PostMapping("salvarDoenca")
    public String salvarDoenca(@ModelAttribute("doencaDTO") DoencaDTO doencaDTO, RedirectAttributes redirectAttributes){
        try {
            Doencas doenca = doencaDTO.mapearDoenca();
            doencaService.saveDoenca(doenca);
            redirectAttributes.addFlashAttribute("Sucesso", "Doença salva com sucesso!");
            return "redirect:/doenca/cadastroDoenca";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("Erro", "Erro ao salvar doença: " + e.getMessage());
            return "redirect:/doenca/cadastroDoenca";
        }
    }

}
