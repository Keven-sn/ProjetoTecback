package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "metodo_pagamento")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(name = "mes_exp", nullable = false)
    private Integer mesExp;

    @Column(name = "ano_exp", nullable = false)
    private Integer anoExp;

    @Column(name = "nome_portador", length = 150)
    private String nomePortador;

    @Column(name = "token_gateway", length = 120)
    private String tokenGateway;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @PrePersist
    void prePersist() {
        this.criadoEm = LocalDateTime.now();
    }
}
