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
        Character castrado,
        Integer animalId,
        Set<Integer> doencaIds,
        Set<Integer> vacinaIds
) {

    public static ProntuarioDTO fromEntity(Prontuarios prontuario) {
        // Converte a lista de entidades Doencas para uma lista de IDs
        Set<Integer> doencasIds = prontuario.getDoencas() == null ? Collections.emptySet() :
                prontuario.getDoencas().stream()
                        .map(Doencas::getId)
                        .collect(Collectors.toSet());

        // O mesmo para as vacinas
        Set<Integer> vacinasIds = prontuario.getVacinas() == null ? Collections.emptySet() :
                prontuario.getVacinas().stream()
                        .map(Vacinas::getId)
                        .collect(Collectors.toSet());

        // Chama o construtor principal do record com os dados convertidos
        return new ProntuarioDTO(
                prontuario.getId(),
                prontuario.getObservacoesGerais(),
                prontuario.getAlergias(),
                prontuario.getDeficiencia(),
                prontuario.getCastrado(),
                prontuario.getAnimal().getId(), // Pega apenas o ID do animal
                doencasIds,
                vacinasIds
        );
    }


}
