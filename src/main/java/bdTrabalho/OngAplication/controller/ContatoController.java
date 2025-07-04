package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.ContatoDTO;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.repository.UsuarioRepository;
import bdTrabalho.OngAplication.service.ContatoService;
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
@RequestMapping("/contato")
@RequiredArgsConstructor
public class ContatoController {

    private final ContatoService contatoService;
    private final UsuarioRepository usuarioRepository;

    @GetMapping("/cadastroContato")
    public String cadastroContato(Model model) {
        model.addAttribute("contatoDTO", new ContatoDTO(null, null, null, null));
        List<Usuarios> usuarios = usuarioRepository.findAll();
        model.addAttribute("usuarios", usuarios);
        return "Cadastros/contatoCadastro";
    }

    @PostMapping("/salvar")
    public String salvarContato(@Valid @ModelAttribute("contatoDTO") ContatoDTO contatoDTO,
                                BindingResult result,
                                RedirectAttributes redirectAttributes,
                                Model model) {

        // Validação de negócio (email duplicado)
        try {
            if (!result.hasErrors()) { // Só executa se a validação inicial passou
                contatoService.salvarContato(contatoDTO);
            }
        } catch (IllegalArgumentException e) {
            result.rejectValue("email", "error.contatoDTO", e.getMessage());
        }

        if (result.hasErrors()) {
            List<Usuarios> usuarios = usuarioRepository.findAll();
            model.addAttribute("usuarios", usuarios);
            return "Cadastros/contatoCadastro";
        }

        redirectAttributes.addFlashAttribute("sucesso", "Contato cadastrado com sucesso!");
        return "redirect:/contato/cadastroContato";
    }
}