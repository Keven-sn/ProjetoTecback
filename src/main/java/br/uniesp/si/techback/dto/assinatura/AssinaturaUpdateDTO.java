package br.uniesp.si.techback.dto.assinatura;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record AssinaturaUpdateDTO(
        @NotNull UUID metodoPagamentoId
) {}
