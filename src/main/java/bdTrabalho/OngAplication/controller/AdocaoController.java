package bdTrabalho.OngAplication.controller;

import bdTrabalho.OngAplication.model.ENUM.PorteAnimal;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;
import bdTrabalho.OngAplication.service.AnimalService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/adocao")
@RequiredArgsConstructor
public class AdocaoController {

    private final AnimalService animalService;

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
}