package br.uniesp.si.techback.dto.usuario;

import br.uniesp.si.techback.dto.EnderecoDTO;
import br.uniesp.si.techback.validation.CPFouCNPJ;
import br.uniesp.si.techback.validation.EnumSubset;
import br.uniesp.si.techback.validation.SenhaForte;
import jakarta.validation.constraints.*;
import java.time.LocalDate;

public record UsuarioCreateDTO(
        @NotBlank @Size(max = 150) String nomeCompleto,
        @NotNull LocalDate dataNascimento,
        @Email @NotBlank @Size(max = 254) String email,
        @SenhaForte @NotBlank @Size(min = 8, max = 60) String senha,
        @CPFouCNPJ String cpfCnpj,
        @EnumSubset (anyOf = {"ADMIN","USER"}) String perfil,
        EnderecoDTO endereco

)
{}
