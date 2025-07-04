package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.EnderecoDTO;
import bdTrabalho.OngAplication.model.ENUM.TipoEndereco;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import bdTrabalho.OngAplication.service.EnderecoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
@RequestMapping("/endereco")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService enderecoService;
    private final UsuarioRepository usuarioRepository;

    private void carregarDadosParaFormulario(Model model) {
        List<Usuarios> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        model.addAttribute("tiposDeEndereco", TipoEndereco.values());
    }

    @GetMapping("/cadastroEndereco")
    public String cadastroEndereco(Model model) {
        model.addAttribute("enderecoDTO", new EnderecoDTO(null, null, null, null, null, null, null, "Brasil", null, null));
        carregarDadosParaFormulario(model);
        return "Cadastros/enderecoCadastro";
    }
    @PostMapping("/salvar")
    public String salvarEndereco(@Valid @ModelAttribute("enderecoDTO") EnderecoDTO enderecoDTO,
                                 BindingResult result,
                                 RedirectAttributes redirectAttributes,
                                 Model model) {

        // Validação de regras de negócio (do Validator) chamado dentro do Service
        try {
            enderecoService.salvarEndereco(enderecoDTO);
        } catch (IllegalArgumentException e) {
            result.rejectValue("cep", "error.enderecoDTO", e.getMessage());
        }


        if (result.hasErrors()) {
            carregarDadosParaFormulario(model);
            return "Cadastros/enderecoCadastro";
        }

        // Sem erros, redireciona
        redirectAttributes.addFlashAttribute("sucesso", "Endereço cadastrado com sucesso!");
        return "redirect:/endereco/cadastroEndereco";
    }
}