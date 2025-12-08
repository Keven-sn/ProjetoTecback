package br.uniesp.si.techback.dto.metodo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import java.util.UUID;

public record MetodoPagamentoCreateDTO(

        UUID usuarioId,

        @NotBlank
        @Size(max = 20)
        String tipo,

        @Size(max = 100)
        String apelido,

        @Size(max = 30)
        String bandeira,

        @Size(max = 20)
        String numero,

        @Size(max = 5)
        String expiracao,

        @Size(max = 120)
        String chavePix

) {}
