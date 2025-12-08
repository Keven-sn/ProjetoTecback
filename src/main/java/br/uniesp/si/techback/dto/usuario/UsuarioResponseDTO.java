package br.uniesp.si.techback.dto.usuario;

import br.uniesp.si.techback.dto.endereco.EnderecoResponseDTO;
import java.time.LocalDate;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nomeCompleto,
        LocalDate dataNascimento,
        String email,
        String cpfCnpj,
        String perfil,
        EnderecoResponseDTO endereco
) {}
