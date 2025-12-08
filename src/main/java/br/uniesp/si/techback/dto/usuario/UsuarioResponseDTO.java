package br.uniesp.si.techback.dto.usuario;

import br.uniesp.si.techback.dto.EnderecoDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nomeCompleto,
        LocalDate dataNascimento,
        String email,
        String cpfCnpj,
        String perfil,
        LocalDateTime criadoEm,
        LocalDateTime atualizadoEm,
        EnderecoDTO endereco
) {}
