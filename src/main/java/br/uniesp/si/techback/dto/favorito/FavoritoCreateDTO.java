package br.uniesp.si.techback.dto.favorito;

import java.util.UUID;

public record FavoritoCreateDTO(
        UUID usuarioId,
        UUID conteudoId
) {}
