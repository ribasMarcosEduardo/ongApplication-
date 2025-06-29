package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.UsuarioDTO;
import bdTrabalho.OngAplication.service.UsuarioService;
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

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario(Model model) {
        model.addAttribute("UsuarioDTO", new UsuarioDTO(null, null, null, null, ' ', null, null));
        return "Cadastros/usuarioCadastro";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@Valid @ModelAttribute("UsuarioDTO") UsuarioDTO usuarioDTO,
                                BindingResult result,
                                RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "Cadastros/usuarioCadastro";
        }

        try {
            // Tenta criar o usuário. Se o Validator do Service lançar um erro (CPF duplicado)...
            usuarioService.criarUsuario(usuarioDTO);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
        } catch (IllegalArgumentException e) {
            result.rejectValue("cpf", "error.usuarioDTO", e.getMessage());
            return "Cadastros/usuarioCadastro";
        }
        // Se tudo deu certo, redireciona para a página de cadastro limpa.
        return "redirect:/usuario/cadastroUsuario";
    }
}