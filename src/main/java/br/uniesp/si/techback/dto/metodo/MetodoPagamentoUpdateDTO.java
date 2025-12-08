package br.uniesp.si.techback.dto.metodo;

import jakarta.validation.constraints.Size;

public record MetodoPagamentoUpdateDTO(

        @Size(max = 100)
        String apelido,

        @Size(max = 30)
        String bandeira,

        @Size(max = 20)
        String numero,

        @Size(max = 5)
        String expiracao,

        @Size(max = 120)
        String chavePix,

        Boolean ativo

) {}
