package br.uniesp.si.techback.dto.metodo;

import java.util.UUID;

public record MetodoPagamentoResponseDTO(
        UUID id,
        UUID usuarioId,
        String bandeira,
        String ultimos4,
        Integer mesExp,
        Integer anoExp
) {}
