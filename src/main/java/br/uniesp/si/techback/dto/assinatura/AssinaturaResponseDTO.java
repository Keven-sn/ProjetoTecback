package br.uniesp.si.techback.dto.assinatura;

import br.uniesp.si.techback.model.enums.StatusAssinatura;

import java.time.LocalDateTime;
import java.util.UUID;

public record AssinaturaResponseDTO(
        UUID id,
        UUID usuarioId,
        UUID planoId,
        StatusAssinatura status,
        LocalDateTime iniciadaEm,
        LocalDateTime canceladaEm
) {}
