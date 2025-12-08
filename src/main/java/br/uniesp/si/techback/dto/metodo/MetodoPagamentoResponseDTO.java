package br.uniesp.si.techback.dto.metodo;

import java.time.LocalDateTime;
import java.util.UUID;

public record MetodoPagamentoResponseDTO(
        UUID id,
        UUID usuarioId,
        String tipo,
        String apelido,
        String bandeira,
        String numeroMascarado,
        String expiracao,
        String chavePix,
        Boolean ativo,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
