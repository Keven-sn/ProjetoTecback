package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.usuario.UsuarioCreateDTO;
import br.uniesp.si.techback.dto.usuario.UsuarioResponseDTO;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class UsuarioService {

    private final UsuarioRepository repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    // CRIAR

    public UsuarioResponseDTO criar(UsuarioCreateDTO dto) {

        if (repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }

        if (dto.cpfCnpj() != null && repository.existsByCpfCnpj(dto.cpfCnpj())) {
            throw new IllegalArgumentException("CPF/CNPJ já cadastrado");
        }

        Usuario usuario = new Usuario();
        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setEmail(dto.email());
        usuario.setSenhaHash(passwordEncoder.encode(dto.senha()));
        usuario.setCpfCnpj(dto.cpfCnpj());
        usuario.setPerfil(dto.perfil());

        return toResponse(repository.save(usuario));
    }


    // BUSCAR POR ID

    public UsuarioResponseDTO buscarPorId(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return toResponse(usuario);
    }


    // MAPEAR

    private UsuarioResponseDTO toResponse(Usuario u) {
        return new UsuarioResponseDTO(
                u.getId(),
                u.getNomeCompleto(),
                u.getEmail(),
                u.getPerfil(),
                u.getCpfCnpj()
        );
    }
}
