package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "assinaturas")
public class Assinatura {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(name = "usuario_id", nullable = false)
    private UUID usuarioId;

    @Column(name = "plano_id", nullable = false)
    private UUID planoId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "metodo_pagamento_id")
    private MetodoPagamento metodoPagamento;

    @Column(nullable = false, length = 20)
    private String status; // ATIVA | EM_ATRASO | CANCELADA

    @Column(name = "iniciada_em", nullable = false)
    private LocalDateTime iniciadaEm;

    @Column(name = "cancelada_em")
    private LocalDateTime canceladaEm;
}
