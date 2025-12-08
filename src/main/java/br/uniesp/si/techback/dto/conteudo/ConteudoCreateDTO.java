package br.uniesp.si.techback.dto.conteudo;

import jakarta.validation.constraints.*;

public record ConteudoCreateDTO(
        @NotBlank @Size(max = 200) String titulo,
        @NotBlank @Pattern(regexp = "FILME|SERIE") String tipo,
        @NotNull @Min(1888) @Max(2100) Integer ano,
        @NotNull @Min(1) @Max(999) Integer duracaoMinutos,
        @NotNull @DecimalMin("0.00") @DecimalMax("10.00") Double relevancia,
        String sinopse,
        @Size(max = 500) String trailerUrl,
        @Size(max = 50) String genero
) {}
