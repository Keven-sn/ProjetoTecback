package br.uniesp.si.techback.dto.conteudo;

import java.math.BigDecimal;

public record ConteudoUpdateDTO(
        String titulo,
        String tipo,
        Integer ano,
        Integer duracaoMinutos,
        BigDecimal relevancia,
        String sinopse,
        String trailerUrl,
        String genero
) {}
