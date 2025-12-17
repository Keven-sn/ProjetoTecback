package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.metodo.MetodoPagamentoCreateDTO;
import br.uniesp.si.techback.dto.metodo.MetodoPagamentoResponseDTO;
import br.uniesp.si.techback.model.MetodoPagamento;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.MetodoPagamentoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MetodoPagamentoService {

    private final MetodoPagamentoRepository repository;
    private final UsuarioRepository usuarioRepository;

    // ============================
    // CRIAR
    // ============================
    public MetodoPagamentoResponseDTO criar(MetodoPagamentoCreateDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        MetodoPagamento m = new MetodoPagamento();
        m.setUsuario(usuario);
        m.setBandeira(dto.bandeira());
        m.setApelido(dto.apelido());
        m.setNumeroMascarado(dto.numeroMascarado());
        m.setUltimos4(dto.ultimos4());
        m.setMesExp(dto.mesExp());
        m.setAnoExp(dto.anoExp());
        m.setNomePortador(dto.nomePortador());


        return toResponse(repository.save(m));
    }


    // ============================
    // LISTAR POR USUÁRIO
    // ============================
    public List<MetodoPagamentoResponseDTO> listar(UUID usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ============================
    // REMOVER
    // ============================
    public void remover(UUID id) {
        repository.deleteById(id);
    }

    // ============================
    // MAPPER
    // ============================
    private MetodoPagamentoResponseDTO toResponse(MetodoPagamento m) {
        return new MetodoPagamentoResponseDTO(
                m.getId(),
                m.getUsuario().getId(),
                m.getBandeira(),
                m.getUltimos4(),
                m.getMesExp(),
                m.getAnoExp()
        );
    }
}
