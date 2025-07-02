package bdTrabalho.OngAplication.dto;

import java.util.List;

// Este DTO é SÓ para exibir os detalhes, por isso o nome.
public record ProntuarioDetalheDTO(
        Boolean castrado,
        String alergias,
        String deficiencia,
        String observacoesGerais,
        List<DoencaDTO> doencas,
        List<VacinaDTO> vacinas
) {}