package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "plano")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Plano {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(nullable = false, unique = true, length = 20)
    private String codigo; // BASICO, PADRAO, PREMIUM

    @Column(nullable = false)
    private Integer limiteDiario;

    @Column(nullable = false)
    private Integer streamsSimultaneos;
}
