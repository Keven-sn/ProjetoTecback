package br.uniesp.si.techback.dto.favorito;

import java.time.LocalDateTime;
import java.util.UUID;

public record FavoritoResponseDTO(
        UUID usuarioId,
        UUID conteudoId,
        LocalDateTime criadoEm
) {}
