package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.CampanhaDTO;
import bdTrabalho.OngAplication.model.Campanhas;
import bdTrabalho.OngAplication.model.EMUN.SituacaoCampanha;
import bdTrabalho.OngAplication.service.CampanhaService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.math.BigDecimal;
import java.util.List;

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

    @GetMapping("/listaCampanha")
    public String listaCampanha(Model model) {

        List<Campanhas> todasAsCampanhas = campanhaService.findAll();

        model.addAttribute("listaCampanhas", todasAsCampanhas);
        return "listagens/listarCampanhas"; // http://localhost:8080/campanha/listaCampanha
    }

    @GetMapping("/editarCampanha")
    public String editarCampanha(@RequestParam("id") int id, Model model) {

        Campanhas campanhaExistente = campanhaService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Campanha não encontrada para o ID: " + id));

        CampanhaDTO campanhaDTO = CampanhaDTO.fromEntity(campanhaExistente);

        model.addAttribute("campanhaDTO", campanhaDTO);
        return "cadastros/campanhaCadastro";
    }

    @GetMapping("/excluir")
    public String deletarCampanha(@RequestParam("id") int id, RedirectAttributes redirectAttributes) {
        try {
            campanhaService.deletarPorId(id);
            redirectAttributes.addFlashAttribute("Sucesso", "Animal excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("Erro", "Erro ao excluir animal. Verifique se ele não possui prontuários ou outros registros associados.");
            e.printStackTrace();
        }

        return "redirect:/campanha/listaCampanha";
    }



}
