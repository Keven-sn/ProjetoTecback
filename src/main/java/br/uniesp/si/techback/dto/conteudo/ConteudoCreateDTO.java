package br.uniesp.si.techback.dto.conteudo;

import jakarta.validation.constraints.*;
import java.math.BigDecimal;

public record ConteudoCreateDTO(
        @NotBlank @Size(max = 200) String titulo,
        @NotBlank String tipo,
        @Min(1888) @Max(2100) int ano,
        @Min(1) @Max(999) int duracaoMinutos,
        @NotNull BigDecimal relevancia,
        String sinopse,
        String trailerUrl,
        String genero
) {}
