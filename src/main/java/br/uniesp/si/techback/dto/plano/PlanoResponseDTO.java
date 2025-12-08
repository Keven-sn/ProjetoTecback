package br.uniesp.si.techback.dto.plano;

import java.util.UUID;

public record PlanoResponseDTO(
        UUID id,
        String nome,
        String descricao,
        Double valorMensal,
        String resolucao,
        Integer dispositivos
) {}
