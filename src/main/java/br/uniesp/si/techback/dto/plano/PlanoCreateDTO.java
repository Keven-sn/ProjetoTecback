package br.uniesp.si.techback.dto.plano;

public record PlanoCreateDTO(
        String codigo,
        Integer limiteDiario,
        Integer streamsSimultaneos
) {}
