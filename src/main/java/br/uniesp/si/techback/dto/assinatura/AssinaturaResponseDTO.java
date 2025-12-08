package br.uniesp.si.techback.dto.assinatura;

import java.time.LocalDateTime;
import java.util.UUID;

public record AssinaturaResponseDTO(
        UUID id,
        UUID usuarioId,
        UUID planoId,
        UUID metodoPagamentoId,
        String status,
        LocalDateTime iniciadaEm,
        LocalDateTime canceladaEm
) {}
