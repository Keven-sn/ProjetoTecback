package br.uniesp.si.techback.dto.plano;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record PlanoUpdateDTO(

        @Size(max = 100)
        String nome,

        @Size(max = 500)
        String descricao,

        @DecimalMin(value = "0.01")
        BigDecimal valorMensal,

        @Size(max = 10)
        String resolucao,

        @Min(1)
        Integer dispositivos
) {}
