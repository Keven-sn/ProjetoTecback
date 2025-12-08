package br.uniesp.si.techback.dto.metodo;

import jakarta.validation.constraints.NotBlank;
import java.util.UUID;

public record MetodoPagamentoCreateDTO(
        UUID usuarioId,
        @NotBlank String tipo,
        String apelido,
        String bandeira,
        String numeroMascarado,
        String expiracao,
        String chavePix
) {}
