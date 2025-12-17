package br.uniesp.si.techback.dto.assinatura;

import java.util.UUID;

public record AssinaturaCreateDTO(
        UUID usuarioId,
        UUID planoId
) {}
