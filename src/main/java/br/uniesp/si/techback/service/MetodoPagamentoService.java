package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.metodo.MetodoPagamentoCreateDTO;
import br.uniesp.si.techback.dto.metodo.MetodoPagamentoResponseDTO;
import br.uniesp.si.techback.model.MetodoPagamento;
import br.uniesp.si.techback.repository.MetodoPagamentoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MetodoPagamentoService {

    private final MetodoPagamentoRepository repository;
    private final UsuarioRepository usuarioRepository;

    public MetodoPagamentoService(MetodoPagamentoRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    public MetodoPagamentoResponseDTO criar(MetodoPagamentoCreateDTO dto) {

        if (!usuarioRepository.existsById(dto.usuarioId())) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        MetodoPagamento m = new MetodoPagamento();
        m.setUsuarioId(dto.usuarioId());
        m.setTipo(dto.tipo());
        m.setApelido(dto.apelido());
        m.setNumeroMascarado(dto.numeroMascarado());
        m.setUltimos4(dto.ultimos4());
        m.setExpiracao(dto.expiracao());
        m.setChavePix(dto.chavePix());

        return toResponse(repository.save(m));
    }

    public List<MetodoPagamentoResponseDTO> listar(UUID usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .stream().map(this::toResponse).toList();
    }

    private MetodoPagamentoResponseDTO toResponse(MetodoPagamento m) {
        return new MetodoPagamentoResponseDTO(
                m.getId(),
                m.getUsuarioId(),
                m.getTipo(),
                m.getApelido(),
                m.getUltimos4(),
                m.getAtivo()
        );
    }
}
