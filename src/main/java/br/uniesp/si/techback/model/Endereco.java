package br.uniesp.si.techback.model;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Entity
@Table(name = "enderecos")
public class Endereco {

    @Id
    @GeneratedValue
    private UUID id;

    @Column(length = 150, nullable = false)
    private String logradouro;

    @Column(length = 20, nullable = false)
    private String numero;

    @Column(length = 150)
    private String complemento;

    @Column(length = 100, nullable = false)
    private String bairro;

    @Column(length = 100, nullable = false)
    private String cidade;

    @Column(length = 2, nullable = false)
    private String estado;

    @Column(length = 9, nullable = false)
    private String cep;
}
