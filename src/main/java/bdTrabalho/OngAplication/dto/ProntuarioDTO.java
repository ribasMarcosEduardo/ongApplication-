package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.Doencas;
import bdTrabalho.OngAplication.model.Prontuarios;
import bdTrabalho.OngAplication.model.Vacinas;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public record ProntuarioDTO(
        int id,
        String observacoesGerais,
        String alergias,
        String deficiencia,
        Boolean castrado,
        Integer animalId,
        Set<Integer> doencaIds,
        Set<Integer> vacinaIds
) {


    public static ProntuarioDTO fromEntity(Prontuarios prontuario) {

        Integer animalId = (prontuario.getAnimal() != null) ? prontuario.getAnimal().getId() : null;

        Set<Integer> doencasIds = (prontuario.getDoencas() != null)
                ? prontuario.getDoencas().stream().map(Doencas::getId).collect(Collectors.toSet())
                : Collections.emptySet();

        Set<Integer> vacinasIds = (prontuario.getVacinas() != null)
                ? prontuario.getVacinas().stream().map(Vacinas::getId).collect(Collectors.toSet())
                : Collections.emptySet();

        return new ProntuarioDTO(
                prontuario.getId(),
                prontuario.getObservacoesGerais(),
                prontuario.getAlergias(),
                prontuario.getDeficiencia(),
                prontuario.getCastrado(),
                prontuario.getAnimal().getId(),
                doencasIds,
                vacinasIds
        );
    }


}
