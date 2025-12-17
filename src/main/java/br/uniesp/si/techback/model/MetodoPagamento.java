package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "metodo_pagamento")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class MetodoPagamento {

    @Id
    @GeneratedValue
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @Column(nullable = false, length = 20)
    private String bandeira;

    @Column(nullable = false, length = 4)
    private String ultimos4;

    @Column(nullable = false)
    private Integer mesExp;

    @Column(nullable = false)
    private Integer anoExp;

    @Column(nullable = false, length = 150)
    private String nomePortador;

    @Column(nullable = false, length = 120)
    private String tokenGateway;

    @Column(nullable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    void prePersist() {
        criadoEm = LocalDateTime.now();
    }
}
