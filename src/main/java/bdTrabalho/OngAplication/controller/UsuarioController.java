package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.AnimalDTO;
import bdTrabalho.OngAplication.dto.UsuarioDTO;
import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.ENUM.Genero;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping("/cadastroUsuario")
    public String cadastroUsuario(Model model) {
        model.addAttribute("UsuarioDTO", new UsuarioDTO(null, null, null, null, null, ' ', null, null));
        model.addAttribute("generos", Genero.values());
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

    @GetMapping("/listaUsuario")
    public String listaUsuario(Model model){
        List<Usuarios> todosOsUsuarios = usuarioService.findAll();
        model.addAttribute("listaUsuarios", todosOsUsuarios);

        return "listagens/listarUsuarios";
    }

    /*@GetMapping("/editarUsuario")
    public String editarUsuario(@RequestParam("id") int id, Model model, RedirectAttributes redirectAttributes) {
        Optional<Usuarios> usuarioOptional = usuarioService.findById(id);
        if (usuarioOptional.isEmpty()) {
            redirectAttributes.addFlashAttribute("erro", "Usuário com ID " + id + " não encontrado.");
            model.addAttribute("usuarioDTO", new UsuarioDTO(null, null, null, null, null, ' ', null, null));
            model.addAttribute("generos", Genero.values());
            return "cadastros/usuarioCadastro";
        }
        UsuarioDTO usuarioDTO = UsuarioDTO.fromEntity(usuarioOptional.get());
        model.addAttribute("usuarioDTO", usuarioDTO);
        model.addAttribute("generos", Genero.values());
        return "cadastros/usuarioCadastro";
    }*/
}