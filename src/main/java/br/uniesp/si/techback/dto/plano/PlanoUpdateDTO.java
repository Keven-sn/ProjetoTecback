package br.uniesp.si.techback.dto.plano;

import jakarta.validation.constraints.*;

public record PlanoUpdateDTO(
        String descricao,
        @DecimalMin("1.00") Double valorMensal,
        String resolucao,
        @Min(1) Integer dispositivos
) {}
