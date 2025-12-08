package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.*;
import br.uniesp.si.techback.dto.usuario.UsuarioCreateDTO;
import br.uniesp.si.techback.dto.usuario.UsuarioResponseDTO;
import br.uniesp.si.techback.model.*;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@Transactional
public class UsuarioService {

    private final UsuarioRepository repository;
    private final EnderecoService enderecoService;

    public UsuarioService(UsuarioRepository repository, EnderecoService enderecoService) {
        this.repository = repository;
        this.enderecoService = enderecoService;
    }

    public UsuarioResponseDTO criar(UsuarioCreateDTO dto) {
        if (repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        Endereco endereco = enderecoService.salvar(dto.endereco());

        Usuario u = Usuario.builder()
                .nomeCompleto(dto.nomeCompleto())
                .dataNascimento(dto.dataNascimento())
                .email(dto.email())
                .senhaHash(BCrypt.hashpw(dto.senha(), BCrypt.gensalt()))
                .cpfCnpj(dto.cpfCnpj())
                .perfil(dto.perfil())
                .criadoEm(LocalDateTime.now())
                .atualizadoEm(LocalDateTime.now())
                .endereco(endereco)
                .build();

        Usuario salvo = repository.save(u);
        return mapToResponse(salvo);
    }

    public UsuarioResponseDTO buscarPorId(UUID id) {
        Usuario u = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return mapToResponse(u);
    }

    public UsuarioResponseDTO atualizar(UUID id, UsuarioCreateDTO dto) {
        Usuario u = repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        u.setNomeCompleto(dto.nomeCompleto());
        u.setDataNascimento(dto.dataNascimento());
        if (!u.getEmail().equals(dto.email()) && repository.existsByEmail(dto.email())) {
            throw new IllegalArgumentException("Email já cadastrado");
        }
        u.setEmail(dto.email());
        if (dto.senha() != null && !dto.senha().isBlank()) {
            u.setSenhaHash(BCrypt.hashpw(dto.senha(), BCrypt.gensalt()));
        }
        u.setCpfCnpj(dto.cpfCnpj());
        u.setPerfil(dto.perfil());
        u.setAtualizadoEm(LocalDateTime.now());
        if (dto.endereco() != null) {
            Endereco e = enderecoService.salvar(dto.endereco());
            u.setEndereco(e);
        }
        Usuario atualizado = repository.save(u);
        return mapToResponse(atualizado);
    }

    public void deletar(UUID id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Usuário não encontrado");
        repository.deleteById(id);
    }

    private UsuarioResponseDTO mapToResponse(Usuario usuario) {
        Endereco e = usuario.getEndereco();
        EnderecoDTO enderecoDTO = null;
        if (e != null) {
            enderecoDTO = new EnderecoDTO(
                    e.getLogradouro(),
                    e.getNumero(),
                    e.getComplemento(),
                    e.getBairro(),
                    e.getCidade(),
                    e.getEstado(),
                    e.getCep()
            );
        }
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getNomeCompleto(),
                usuario.getDataNascimento(),
                usuario.getEmail(),
                usuario.getCpfCnpj(),
                usuario.getPerfil(),
                usuario.getCriadoEm(),
                usuario.getAtualizadoEm(),
                enderecoDTO
        );
    }
}
