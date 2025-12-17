package br.uniesp.si.techback.dto.usuario;

import br.uniesp.si.techback.model.enums.PerfilUsuario;

import java.util.UUID;

public record UsuarioResponseDTO(
        UUID id,
        String nomeCompleto,
        String email,
        PerfilUsuario perfil,
        String cpfCnpj
) {}
