package br.uniesp.si.techback.dto.conteudo;

import br.uniesp.si.techback.model.enums.TipoConteudo;

import java.math.BigDecimal;
import java.util.UUID;

public record ConteudoResponseDTO(
        UUID id,
        String titulo,
        TipoConteudo tipo,
        String genero,
        Integer ano,
        BigDecimal relevancia
) {}
