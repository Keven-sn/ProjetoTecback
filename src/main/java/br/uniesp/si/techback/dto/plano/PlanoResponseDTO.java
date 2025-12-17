package br.uniesp.si.techback.dto.plano;

import java.util.UUID;

public record PlanoResponseDTO(
        UUID id,
        String codigo,
        Integer limiteDiario,
        Integer streamsSimultaneos
) {}
