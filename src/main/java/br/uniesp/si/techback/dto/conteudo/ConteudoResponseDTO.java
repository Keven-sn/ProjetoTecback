package br.uniesp.si.techback.dto.conteudo;

import java.util.UUID;

public record ConteudoResponseDTO(
        UUID id,
        String titulo,
        String tipo,
        Integer ano,
        Integer duracaoMinutos,
        Double relevancia,
        String sinopse,
        String trailerUrl,
        String genero
) {}
