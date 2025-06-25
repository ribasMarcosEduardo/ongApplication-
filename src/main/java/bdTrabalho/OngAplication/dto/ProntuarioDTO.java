package bdTrabalho.OngAplication.dto;

import java.util.Set;

public record ProntuarioDTO(
        int id,
        String observacoesGerais,
        String alergias,
        String deficiencia,
        Character castrado,
        Integer animalId,
        Set<Integer> doencaIds,
        Set<Integer> vacinaIds
) {


}
