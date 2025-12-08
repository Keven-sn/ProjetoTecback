package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.assinatura.AssinaturaCreateDTO;
import br.uniesp.si.techback.dto.assinatura.AssinaturaResponseDTO;
import br.uniesp.si.techback.model.Assinatura;
import br.uniesp.si.techback.model.MetodoPagamento;
import br.uniesp.si.techback.repository.AssinaturaRepository;
import br.uniesp.si.techback.repository.MetodoPagamentoRepository;
import br.uniesp.si.techback.repository.PlanoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;
    private final MetodoPagamentoRepository metodoPagamentoRepository;

    public AssinaturaService(
            AssinaturaRepository assinaturaRepository,
            UsuarioRepository usuarioRepository,
            PlanoRepository planoRepository,
            MetodoPagamentoRepository metodoPagamentoRepository) {
        this.assinaturaRepository = assinaturaRepository;
        this.usuarioRepository = usuarioRepository;
        this.planoRepository = planoRepository;
        this.metodoPagamentoRepository = metodoPagamentoRepository;
    }

    public AssinaturaResponseDTO criar(AssinaturaCreateDTO dto) {
        UUID usuarioId = dto.usuarioId();
        UUID planoId = dto.planoId();
        UUID metodoId = dto.metodoPagamentoId();

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        if (!planoRepository.existsById(planoId)) {
            throw new EntityNotFoundException("Plano não encontrado");
        }

        MetodoPagamento metodo = metodoPagamentoRepository.findById(metodoId)
                .orElseThrow(() -> new EntityNotFoundException("Método de pagamento não encontrado"));

        if (!metodo.getUsuario().getId().equals(usuarioId)) {
            throw new IllegalArgumentException("Método de pagamento não pertence ao usuário informado");
        }

        boolean possuiAtiva = assinaturaRepository.findByUsuarioId(usuarioId)
                .stream()
                .anyMatch(a -> "ATIVA".equals(a.getStatus()));

        if (possuiAtiva) {
            throw new IllegalArgumentException("Usuário já possui uma assinatura ativa");
        }

        boolean cobrancaOk = executarCobrancaMock(metodo, planoId);

        String status = cobrancaOk ? "ATIVA" : "EM_ATRASO";

        Assinatura assinatura = new Assinatura();
        assinatura.setUsuarioId(usuarioId);
        assinatura.setPlanoId(planoId);
        assinatura.setMetodoPagamento(metodo);
        assinatura.setStatus(status);
        assinatura.setIniciadaEm(LocalDateTime.now());
        assinatura.setCanceladaEm(null);

        Assinatura salvo = assinaturaRepository.save(assinatura);

        return toResponse(salvo);
    }

    public AssinaturaResponseDTO cancelar(UUID id) {
        Assinatura a = assinaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada"));

        if ("CANCELADA".equals(a.getStatus())) {
            throw new IllegalArgumentException("Assinatura já está cancelada");
        }

        a.setStatus("CANCELADA");
        a.setCanceladaEm(LocalDateTime.now());
        assinaturaRepository.save(a);
        return toResponse(a);
    }

    public List<AssinaturaResponseDTO> listarPorStatus(String status) {
        return assinaturaRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    public List<AssinaturaResponseDTO> listarPorUsuario(UUID usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        return assinaturaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    private AssinaturaResponseDTO toResponse(Assinatura a) {
        UUID metodoId = a.getMetodoPagamento() != null ? a.getMetodoPagamento().getId() : null;
        return new AssinaturaResponseDTO(
                a.getId(),
                a.getUsuarioId(),
                a.getPlanoId(),
                metodoId,
                a.getStatus(),
                a.getIniciadaEm(),
                a.getCanceladaEm()
        );
    }

    private boolean executarCobrancaMock(MetodoPagamento metodo, UUID planoId) {
        try {
            String ultimos4 = metodo.getUltimos4();
            if (ultimos4 == null || ultimos4.isBlank()) return false;
            char last = ultimos4.charAt(ultimos4.length() - 1);
            return last != '0';
        } catch (Exception e) {
            return false;
        }
    }
}
