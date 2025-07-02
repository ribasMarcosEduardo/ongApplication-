package bdTrabalho.OngAplication.dto;

import bdTrabalho.OngAplication.model.ENUM.PorteAnimal;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;

import java.time.LocalDateTime;

// O DTO principal que será retornado na API
public record AnimalDetalheDTO(
        Integer id,
        String nome,
        String descricao,
        String raca,
        Integer idade,
        TipoAnimal tipo,
        String foto,
        Double peso,
        Character sexo,
        PorteAnimal porte,
        LocalDateTime dataChegada,
        String historia,
        ProntuarioDetalheDTO prontuario
) {}