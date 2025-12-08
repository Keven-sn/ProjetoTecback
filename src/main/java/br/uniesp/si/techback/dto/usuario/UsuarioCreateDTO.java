package br.uniesp.si.techback.dto.usuario;

import br.uniesp.si.techback.dto.endereco.EnderecoCreateDTO;
import br.uniesp.si.techback.validation.CPFouCNPJ;
import br.uniesp.si.techback.validation.EnumSubset;
import br.uniesp.si.techback.validation.SenhaForte;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UsuarioCreateDTO(
        @NotBlank @Size(max = 150) String nomeCompleto,
        @NotNull LocalDate dataNascimento,
        @NotBlank @Email @Size(max = 254) String email,
        @NotBlank @SenhaForte String senha,
        @CPFouCNPJ String cpfCnpj,
        @NotBlank @EnumSubset(anyOf = {"ADMIN", "USER"}) String perfil,
        EnderecoCreateDTO endereco
) {}
