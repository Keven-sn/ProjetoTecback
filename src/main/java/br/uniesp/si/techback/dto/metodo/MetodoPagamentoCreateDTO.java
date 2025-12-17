package br.uniesp.si.techback.dto.metodo;

import java.util.UUID;

public record MetodoPagamentoCreateDTO(
        UUID usuarioId,
        String bandeira,
        String apelido,
        String numeroMascarado,
        String ultimos4,
        Integer mesExp,
        Integer anoExp,
        String nomePortador
) {}

