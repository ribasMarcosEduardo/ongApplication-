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

import java.util.HashSet;
import java.util.List;

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
        List<Doencas> allDoencasEntidades = doencaService.findAll();
        List<Vacinas> allVacinasEntidades = vacinaService.findAll();

        List<DoencaDTO> allDoencasDTO = allDoencasEntidades.stream()
                .map(DoencaDTO::fromEntity)
                .toList();
        List<VacinaDTO> allVacinasDTO = allVacinasEntidades.stream()
                .map(VacinaDTO::fromEntity)
                .toList();

        model.addAttribute("prontuarioDTO", new ProntuarioDTO(0, null, null, null, false, null, new HashSet<>(), new HashSet<>()));
        model.addAttribute("animalNome", null);
        model.addAttribute("allAnimais", allAnimais);
        model.addAttribute("allDoencas", allDoencasDTO);
        model.addAttribute("allVacinas", allVacinasDTO);

        return "Cadastros/prontuarioCadastro";
    }

    @PostMapping("/salvarProntuario")
    public String salvarProntuario(@ModelAttribute("prontuarioDTO") ProntuarioDTO prontuarioDTO, RedirectAttributes redirectAttributes) {
        try {
            prontuarioService.saveProntuario(prontuarioDTO);
            redirectAttributes.addFlashAttribute("Sucesso", "Prontuário salvo com sucesso!");
            return "redirect:/animal/listaAnimal";
        } catch (Exception e) {
            e.printStackTrace();
            redirectAttributes.addFlashAttribute("Erro", "Erro ao salvar prontuário: " + e.getMessage());
            return "redirect:/animal/listaAnimal";
        }
    }

    @GetMapping("/editar")
    public String editarProntuario(@RequestParam("animalId") Integer animalId, Model model) {

        Animais animal = animalService.findById(animalId)
                .orElseThrow(() -> new IllegalArgumentException("Animal não encontrado: " + animalId));

        Prontuarios prontuario = prontuarioService.findByAnimalId(animalId)
                .orElseGet(() -> {

                    Prontuarios novoProntuario = new Prontuarios();
                    novoProntuario.setAnimal(animal);

                    novoProntuario.setCastrado(false);
                    return novoProntuario;
                });

        ProntuarioDTO prontuarioDTO = ProntuarioDTO.fromEntity(prontuario);

        List<DoencaDTO> allDoencasDTO = doencaService.findAll().stream().map(DoencaDTO::fromEntity).toList();
        List<VacinaDTO> allVacinasDTO = vacinaService.findAll().stream().map(VacinaDTO::fromEntity).toList();

        model.addAttribute("prontuarioDTO", prontuarioDTO);
        model.addAttribute("animalNome", animal.getNome());
        model.addAttribute("allDoencas", allDoencasDTO);
        model.addAttribute("allVacinas", allVacinasDTO);

        return "Cadastros/prontuarioCadastro";
    }
}
