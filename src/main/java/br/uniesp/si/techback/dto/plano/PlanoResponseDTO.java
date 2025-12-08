package br.uniesp.si.techback.dto.plano;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PlanoResponseDTO(
        UUID id,
        String nome,
        String descricao,
        BigDecimal valorMensal,
        String resolucao,
        Integer dispositivos,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
