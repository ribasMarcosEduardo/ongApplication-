package bdTrabalho.OngAplication.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record AdocaoDTO(
        Integer id,
        LocalDateTime dataAdocao,
        String observacoes,
        Integer animalId,
        Integer usuarioId
) {}