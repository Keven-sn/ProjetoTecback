package br.uniesp.si.techback.model;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class FavoritoId implements Serializable {

    private UUID usuarioId;
    private UUID conteudoId;
}
