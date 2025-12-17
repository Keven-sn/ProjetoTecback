package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.endereco.EnderecoCreateDTO;
import br.uniesp.si.techback.dto.endereco.EnderecoResponseDTO;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.EnderecoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EnderecoService {

    private final EnderecoRepository repository;
    private final UsuarioRepository usuarioRepository;

    // ============================
    // CRIAR
    // ============================
    public EnderecoResponseDTO criar(EnderecoCreateDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Endereco endereco = new Endereco();
        endereco.setUsuario(usuario);
        endereco.setLogradouro(dto.logradouro());
        endereco.setNumero(dto.numero());
        endereco.setComplemento(dto.complemento());
        endereco.setBairro(dto.bairro());
        endereco.setCidade(dto.cidade());
        endereco.setEstado(dto.estado());
        endereco.setCep(dto.cep());

        return toResponse(repository.save(endereco));
    }

    // ============================
    // BUSCAR POR ID
    // ============================
    public EnderecoResponseDTO buscar(UUID id) {
        Endereco endereco = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));
        return toResponse(endereco);
    }

    // ============================
    // LISTAR POR USUÁRIO
    // ============================
    public List<EnderecoResponseDTO> listarPorUsuario(UUID usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ============================
    // EXCLUIR
    // ============================
    public void excluir(UUID id) {
        repository.deleteById(id);
    }

    // ============================
    // MAPPER
    // ============================
    private EnderecoResponseDTO toResponse(Endereco e) {
        return new EnderecoResponseDTO(
                e.getId(),
                e.getLogradouro(),
                e.getNumero(),
                e.getComplemento(),
                e.getBairro(),
                e.getCidade(),
                e.getEstado(),
                e.getCep()
        );
    }
}
