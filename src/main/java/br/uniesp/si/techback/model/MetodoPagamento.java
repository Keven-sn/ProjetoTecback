package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.UuidGenerator;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "metodos_pagamento")
public class MetodoPagamento {

    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @Column(nullable = false, length = 20)
    private String tipo; // CARTAO | PIX | BOLETO

    @Column(length = 100)
    private String apelido;

    @Column(length = 30)
    private String bandeira;

    @Column(name = "numero_mascarado", length = 20)
    private String numeroMascarado;

    @Column(length = 5)
    private String expiracao;

    @Column(name = "chave_pix", length = 120)
    private String chavePix;

    @Column(nullable = false)
    private boolean ativo;

    @Column(name = "criado_em", nullable = false)
    private LocalDateTime criadoEm;

    @Column(name = "atualizado_em", nullable = false)
    private LocalDateTime atualizadoEm;

    @PrePersist
    public void prePersist() {
        criadoEm = LocalDateTime.now();
        atualizadoEm = LocalDateTime.now();
        ativo = true;
    }

    @PreUpdate
    public void preUpdate() {
        atualizadoEm = LocalDateTime.now();
    }

    public String getUltimos4() {
        return null;
    }
}
