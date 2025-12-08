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
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "conteudo")
public class Conteudo {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 200, nullable = false)
    private String titulo;

    @Column(length = 10, nullable = false)
    private String tipo;

    @Column(nullable = false)
    private Integer ano;

    @Column(name = "duracao_minutos", nullable = false)
    private Integer duracaoMinutos;

    @Column(nullable = false)
    private Double relevancia;

    @Column(columnDefinition = "TEXT")
    private String sinopse;

    @Column(name = "trailer_url", length = 500)
    private String trailerUrl;

    @Column(length = 50)
    private String genero;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;
}
