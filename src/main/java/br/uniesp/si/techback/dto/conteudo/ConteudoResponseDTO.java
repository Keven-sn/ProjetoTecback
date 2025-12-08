package br.uniesp.si.techback.dto.conteudo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ConteudoResponseDTO(
        UUID id,
        String titulo,
        String tipo,
        int ano,
        int duracaoMinutos,
        BigDecimal relevancia,
        String sinopse,
        String trailerUrl,
        String genero,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm
) {}
