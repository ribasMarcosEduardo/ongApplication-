package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.DoencaDTO;
import bdTrabalho.OngAplication.dto.VacinaDTO;
import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.Doencas;
import bdTrabalho.OngAplication.model.Prontuarios;
import bdTrabalho.OngAplication.model.Vacinas;
import bdTrabalho.OngAplication.dto.ProntuarioDTO;
import bdTrabalho.OngAplication.service.AnimalService;
import bdTrabalho.OngAplication.service.DoencaService;
import bdTrabalho.OngAplication.service.ProntuarioService;
import bdTrabalho.OngAplication.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Controller
@RequestMapping("/prontuario")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;
    private final AnimalService animalService;
    private final DoencaService doencaService;
    private final VacinaService vacinaService;

    @GetMapping("/cadastroProntuario")
    public String cadastroProntuario(Model model) {

        List<Animais> allAnimais = animalService.findAll();
        List<Doencas> allDoencas = doencaService.findAll();
        List<Vacinas> allVacinas = vacinaService.findAll();

        model.addAttribute("prontuarioDTO", new ProntuarioDTO(0, null, null, null, 'N', null, null, null));
        model.addAttribute("allAnimais", allAnimais);
        model.addAttribute("allDoencas", allDoencas);
        model.addAttribute("allVacinas", allVacinas);

        return "Cadastros/prontuarioCadastro"; //http://localhost:8080/prontuario/cadastroProntuario
    }

    @PostMapping("/salvarProntuario")
    public String salvarProntuario(@ModelAttribute("prontuarioDTO") ProntuarioDTO prontuarioDTO, RedirectAttributes redirectAttributes) {
        try {
            prontuarioService.saveProntuario(prontuarioDTO);
            redirectAttributes.addFlashAttribute("Sucesso", "Prontuário salvo com sucesso!");
            return "redirect:/prontuario/cadastroProntuario";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("Erro", "Erro ao salvar prontuário: " + e.getMessage());
            return "redirect:/prontuario/cadastroProntuario";
        }
    }



}
