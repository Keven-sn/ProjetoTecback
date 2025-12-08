package br.uniesp.si.techback.dto.plano;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PlanoCreateDTO(

        @NotBlank
        @Size(max = 100)
        String nome,

        @Size(max = 500)
        String descricao,

        @NotNull
        @DecimalMin(value = "0.01")
        BigDecimal valorMensal,

        @NotBlank
        @Size(max = 10)
        String resolucao,

        @NotNull
        @Min(1)
        Integer dispositivos
) {}
