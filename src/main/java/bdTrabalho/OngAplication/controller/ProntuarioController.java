package bdTrabalho.OngAplication.Controller;

import bdTrabalho.OngAplication.Model.Animais;
import bdTrabalho.OngAplication.Model.Doencas;
import bdTrabalho.OngAplication.Model.Vacinas;
import bdTrabalho.OngAplication.dto.ProntuarioDTO;
import bdTrabalho.OngAplication.service.AnimalService;
import bdTrabalho.OngAplication.service.DoencaService;
import bdTrabalho.OngAplication.service.ProntuarioService;
import bdTrabalho.OngAplication.service.VacinaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/prontuario")
public class ProntuarioCadastro {

    private final ProntuarioService prontuarioService;
    private final AnimalService animalService;
    private final DoencaService doencaService;
    private final VacinaService vacinaService;

    @GetMapping("/cadastro")
    public String formCadastro(Model model) {
        // Carrega as listas necessárias para os modais e o select
        List<Animais> allAnimais = animalService.findAll(); // Crie este método no seu serviço
        List<Doencas> allDoencas = doencaService.findAll(); // Crie este método
        List<Vacinas> allVacinas = vacinaService.findAll(); // Crie este método

        // Adiciona as listas e o DTO ao modelo
        model.addAttribute("prontuarioDTO", new ProntuarioDTO(0, null, null, null, null, null, null, null));
        model.addAttribute("allAnimais", allAnimais);
        model.addAttribute("allDoencas", allDoencas);
        model.addAttribute("allVacinas", allVacinas);

        return "Cadastros/cadastroProntuario";
    }
}
