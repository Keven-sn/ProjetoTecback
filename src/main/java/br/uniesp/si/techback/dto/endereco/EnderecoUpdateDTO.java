package br.uniesp.si.techback.dto.endereco;

import jakarta.validation.constraints.Size;

public record EnderecoUpdateDTO(
        @Size(max = 150) String logradouro,
        @Size(max = 20) String numero,
        @Size(max = 150) String complemento,
        @Size(max = 100) String bairro,
        @Size(max = 100) String cidade,
        @Size(min = 2, max = 2) String estado,
        @Size(min = 8, max = 9) String cep
) {}
