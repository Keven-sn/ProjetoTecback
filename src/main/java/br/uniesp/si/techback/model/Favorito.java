package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"usuarioId", "conteudoId"})
@Entity
@Table(name = "favoritos")
public class Favorito {

    @Id
    @GeneratedValue
    private UUID id; // OPCIONAL – pode remover e usar PK composta

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "conteudo_id", nullable = false)
    private UUID conteudoId;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;
}
