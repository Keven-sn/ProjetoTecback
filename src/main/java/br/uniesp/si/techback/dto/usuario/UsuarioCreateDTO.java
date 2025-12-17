package br.uniesp.si.techback.dto.usuario;

import br.uniesp.si.techback.model.enums.PerfilUsuario;

import java.time.LocalDate;

public record UsuarioCreateDTO(
        String nomeCompleto,
        LocalDate dataNascimento,
        String email,
        String senha,
        String cpfCnpj,
        PerfilUsuario perfil
) {}
