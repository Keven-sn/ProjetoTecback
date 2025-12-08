package br.uniesp.si.techback.dto.plano;

import jakarta.validation.constraints.*;

public record PlanoCreateDTO(
        @NotBlank String nome,
        String descricao,
        @NotNull @DecimalMin("1.00") Double valorMensal,
        @NotBlank String resolucao,
        @NotNull @Min(1) Integer dispositivos
) {}
