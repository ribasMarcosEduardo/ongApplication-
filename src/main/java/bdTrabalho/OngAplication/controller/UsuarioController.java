//package bdTrabalho.OngAplication.controller;
//
//import bdTrabalho.OngAplication.dto.UsuarioDTO;
//import bdTrabalho.OngAplication.service.UsuarioService;
//import jakarta.validation.Valid;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping("/usuario")
//public class UsuarioController {
//
//    @Autowired
//    private UsuarioService usuarioService;
//
//    @GetMapping("/cadastroUsuario")
//    public String cadastroUsuario(Model model) {
//        model.addAttribute("UsuarioDTO", new bdTrabalho.OngAplication.dto.UsuarioDTO());
//        return "Cadastros/usuarioCadastro";
//    }
//
//    @PostMapping("/salvar")
//    public String salvarUsuario(@Valid @ModelAttribute("UsuarioDTO") UsuarioDTO usuarioDTO,
//                                BindingResult result,
//                                RedirectAttributes redirectAttributes) {
//        if (result.hasErrors()) {
//            return "Cadastros/usuarioCadastro";
//        }
//
//        try {
//            usuarioService.criarUsuario(usuarioDTO);
//            //Mensagem que aparece depois de cadastrar com sucesso
//            redirectAttributes.addFlashAttribute("sucesso", "Usuário cadastrado com sucesso!");
//        } catch (IllegalArgumentException e) {
//            //Validação de erros
//            result.rejectValue("cpf", "error.usuarioDTO", e.getMessage());
//            return "Cadastros/usuarioCadastro";
//        }
//
//        //Volta para a página de cadastro após ser salvo
//        return "redirect:/usuario/cadastroUsuario";
//    }
//}
