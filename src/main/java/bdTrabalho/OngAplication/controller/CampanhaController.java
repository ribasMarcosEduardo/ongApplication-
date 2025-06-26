package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.CampanhaDTO;
import bdTrabalho.OngAplication.model.EMUN.SituacaoCampanha;
import bdTrabalho.OngAplication.service.CampanhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Controller
@RequestMapping("/campanha")
public class CampanhaController {

    private final CampanhaService campanhaService;

    @GetMapping("cadastroCampanha")
    public String cadastroCampanha(Model model) {
        model.addAttribute("campanhaDTO", new CampanhaDTO(0, null, null, null, null, null,
                SituacaoCampanha.AGUARDANDO, null, new BigDecimal("0.00"), new BigDecimal("0.00")));
        return "cadastros/campanhaCadastro";  // http://localhost:8080/campanha/cadastroCampanha
    }

    @PostMapping("/salvarCampanha")
    public String salvarCampanha(@ModelAttribute("campanhaDTO") CampanhaDTO campanhaDTO, RedirectAttributes redirectAttributes) {
        try {
            campanhaService.saveCampanha(campanhaDTO);
            redirectAttributes.addFlashAttribute("Sucesso", "Campanha salva com sucesso!");
            return "redirect:/campanha/cadastroCampanha";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("Erro", "Erro ao salvar campanha: " + e.getMessage());
            return "redirect:/campanha/cadastroCampanha";
        }
    }


}
