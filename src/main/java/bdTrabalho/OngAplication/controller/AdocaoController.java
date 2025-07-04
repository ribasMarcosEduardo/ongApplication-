package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.dto.AdocaoDTO;
import bdTrabalho.OngAplication.model.Adocoes;
import bdTrabalho.OngAplication.model.Animais;
import bdTrabalho.OngAplication.model.ENUM.PorteAnimal;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;
import bdTrabalho.OngAplication.model.Usuarios;
import bdTrabalho.OngAplication.service.AdocaoService;
import bdTrabalho.OngAplication.service.AnimalService;
import bdTrabalho.OngAplication.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/adocao")
@RequiredArgsConstructor
public class AdocaoController {

    private final AdocaoService adocaoService;
    private final AnimalService animalService;
    private final UsuarioService usuarioService;

    @GetMapping
    public String mostrarVitrine(
            Model model,
            // Recebe os parâmetros de filtro da URL.
            @RequestParam(required = false) TipoAnimal tipo,
            @RequestParam(required = false) String cor
    )

    {
        // Dados para preencher os filtros na tela
        model.addAttribute("tiposDeAnimal", TipoAnimal.values());
        model.addAttribute("portesDeAnimal", PorteAnimal.values());
        // Adiciona os filtros atuais ao modelo para manter os <select> selecionados
        model.addAttribute("filtroTipo", tipo);
        model.addAttribute("filtroCor", cor);

        // Busca os animais aplicando os filtros recebidos e envia para a tela
        model.addAttribute("animaisFiltrados", animalService.findAnimaisDisponiveisComFiltros(tipo, cor));

        return "listagens/adocao";
    }

    @GetMapping("/cadastrar")
    public String mostrarFormularioAdocao(Model model) {
        List<Animais> animaisDisponiveis = animalService.findAllFilteredBySituacao("D");
        List<Usuarios> todosUsuarios = usuarioService.findAll();

        model.addAttribute("adocaoDTO", new AdocaoDTO(null, LocalDateTime.now(), "", null, null));
        model.addAttribute("animaisDisponiveis", animaisDisponiveis);
        model.addAttribute("todosUsuarios", todosUsuarios);

        return "Cadastros/adocaoCadastro";
    }

    @PostMapping("/salvar")
    public String salvarAdocao(@Valid @ModelAttribute("adocaoDTO") AdocaoDTO adocaoDTO,
                               BindingResult result,
                               RedirectAttributes redirectAttributes,
                               Model model) {

        if (result.hasErrors()) {
            model.addAttribute("animaisDisponiveis", animalService.findAllFilteredBySituacao("D"));
            model.addAttribute("todosUsuarios", usuarioService.findAll());
            return "Cadastros/adocaoCadastro";
        }

        try {
            adocaoService.solicitarAdocao(adocaoDTO);
            redirectAttributes.addFlashAttribute("sucesso", "Solicitação de adoção registrada com sucesso! Aguardando análise.");
            return "redirect:/animal/listaAnimal";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao registrar adoção: " + e.getMessage());
            return "redirect:/animal/listaAnimal";
        }
    }

    @GetMapping("/listar")
    public String listarAdocoes(Model model) {
        // Busca todas as adoções. Você pode filtrar aqui se quiser, ex: só as "EM_ANALISE"
        List<Adocoes> todasAsAdocoes = adocaoService.findAll();
        model.addAttribute("listaAdocoes", todasAsAdocoes);
        return "listagens/listarAdocoes";
    }

    @PostMapping("/aprovar")
    public String aprovarAdocao(@RequestParam("adocaoId") int adocaoId, RedirectAttributes redirectAttributes) {
        try {
            adocaoService.aprovarAdocao(adocaoId);
            redirectAttributes.addFlashAttribute("sucesso", "Adoção aprovada com sucesso!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao aprovar adoção: " + e.getMessage());
        }
        return "redirect:/adocao/listar";
    }

    @PostMapping("/reprovar")
    public String reprovarAdocao(@RequestParam("adocaoId") int adocaoId,
                                 @RequestParam("motivo") String motivo,
                                 RedirectAttributes redirectAttributes) {
        try {
            adocaoService.reprovarAdocao(adocaoId, motivo);
            redirectAttributes.addFlashAttribute("sucesso", "Adoção reprovada.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erro", "Erro ao reprovar adoção: " + e.getMessage());
        }
        return "redirect:/adocao/listar";
    }
}