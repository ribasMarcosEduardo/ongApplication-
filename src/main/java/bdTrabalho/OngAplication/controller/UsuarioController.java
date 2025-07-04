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
    public String mostrarFormularioDeCadastro(Model model) {

        model.addAttribute("usuarioDTO", new UsuarioDTO(null, null, null, null, null, null, null));

        model.addAttribute("generos", Genero.values());

        return "Cadastros/usuarioCadastro";
    }

    @PostMapping("/salvar")
    public String salvarUsuario(@Valid @ModelAttribute("usuarioDTO") UsuarioDTO usuarioDTO,
                                BindingResult result,
                                Model model,
                                RedirectAttributes redirectAttributes) {

        if (result.hasErrors()) {
            model.addAttribute("generos", Genero.values());
            return "Cadastros/usuarioCadastro";
        }

        try {
            // Chamada única para o service, que agora resolve tudo!
            usuarioService.salvar(usuarioDTO);

            // Mensagem de sucesso dinâmica
            String mensagem = (usuarioDTO.id() == null || usuarioDTO.id() == 0) ?
                    "Usuário cadastrado com sucesso!" :
                    "Usuário atualizado com sucesso!";
            redirectAttributes.addFlashAttribute("sucesso", mensagem);

        } catch (IllegalArgumentException e) {
            result.rejectValue("cpf", "error.usuarioDTO", e.getMessage());
            model.addAttribute("generos", Genero.values());
            return "Cadastros/usuarioCadastro";
        }

        return "redirect:/usuario/listaUsuario";
    }

    @GetMapping("/editar")
    public String mostrarFormularioDeEdicao(@RequestParam("id") Integer id, Model model) {
        // 1. Busca a entidade 'Usuarios' no banco de dados.
        Usuarios usuarioExistente = usuarioService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("ID de usuário inválido: " + id));

        // 2. Converte a entidade para o DTO usando seu método estático.
        UsuarioDTO usuarioDTO = UsuarioDTO.fromEntity(usuarioExistente);

        // 3. Adiciona o DTO ao modelo. O formulário usará o th:object="${usuarioDTO}".
        model.addAttribute("usuarioDTO", usuarioDTO);

        // 4. Adiciona a lista de gêneros para o dropdown no formulário.
        model.addAttribute("generos", Genero.values());

        // 5. Retorna o caminho para a página de formulário.
        return "Cadastros/usuarioCadastro";
    }

    @GetMapping("/listaUsuario")
    public String listaUsuario(Model model){
        List<Usuarios> todosOsUsuarios = usuarioService.findAll();
        model.addAttribute("listaUsuarios", todosOsUsuarios);

        return "listagens/listarUsuarios";
    }

    @GetMapping("/excluir")
    public String excluirUsuario(@RequestParam("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            usuarioService.excluirPorId(id);
            redirectAttributes.addFlashAttribute("sucesso", "Usuário excluído com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao excluir usuário: " + e.getMessage());
        }

        return "redirect:/usuario/listaUsuario";
    }
}