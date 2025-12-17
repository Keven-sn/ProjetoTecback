package br.uniesp.si.techback.dto.conteudo;

import br.uniesp.si.techback.model.enums.TipoConteudo;

import java.math.BigDecimal;

public record ConteudoCreateDTO(
        String titulo,
        TipoConteudo tipo,
        Integer ano,
        Integer duracaoMinutos,
        BigDecimal relevancia,
        String sinopse,
        String genero,
        String trailerUrl
) {}