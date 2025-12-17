package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.assinatura.AssinaturaCreateDTO;
import br.uniesp.si.techback.dto.assinatura.AssinaturaUpdateDTO;
import br.uniesp.si.techback.dto.assinatura.AssinaturaResponseDTO;
import br.uniesp.si.techback.model.Assinatura;
import br.uniesp.si.techback.model.MetodoPagamento;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.model.Usuario;
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
            MetodoPagamentoRepository metodoPagamentoRepository
    ) {
        this.assinaturaRepository = assinaturaRepository;
        this.usuarioRepository = usuarioRepository;
        this.planoRepository = planoRepository;
        this.metodoPagamentoRepository = metodoPagamentoRepository;
    }

    // ========================================================================
    // CRIAR ASSINATURA
    // ========================================================================
    public AssinaturaResponseDTO criar(AssinaturaCreateDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Plano plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));

        MetodoPagamento metodo = metodoPagamentoRepository.findById(dto.metodoPagamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Método de pagamento não encontrado"));

        if (!metodo.getUsuario().getId().equals(usuario.getId())) {
            throw new IllegalArgumentException("Método de pagamento não pertence ao usuário informado");
        }

        // O usuário só pode ter 1 assinatura ativa
        boolean possuiAtiva = assinaturaRepository
                .findByUsuarioId(usuario.getId())
                .stream()
                .anyMatch(a -> "ATIVA".equals(a.getStatus()));

        if (possuiAtiva) {
            throw new IllegalArgumentException("Usuário já possui uma assinatura ativa");
        }

        // Mock de cobrança bancária
        boolean cobrado = executarCobrancaMock(metodo);

        Assinatura nova = new Assinatura();
        nova.SetUsurio(usuario);
        nova.setPlano(plano);
        nova.setMetodoPagamento(metodo);
        nova.setStatus(cobrado ? "ATIVA" : "EM_ATRASO");
        nova.setIniciadaEm(LocalDateTime.now());
        nova.setCanceladaEm(null);

        Assinatura salva = assinaturaRepository.save(nova);
        return toResponse(salva);
    }

    // ========================================================================
    // CANCELAR ASSINATURA
    // ========================================================================
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

    // ========================================================================
    // LISTAR POR STATUS
    // ========================================================================
    public List<AssinaturaResponseDTO> listarPorStatus(String status) {
        return assinaturaRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ========================================================================
    // LISTAR POR USUÁRIO
    // ========================================================================
    public List<AssinaturaResponseDTO> listarPorUsuario(UUID usuarioId) {

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        return assinaturaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    // ========================================================================
    // TROCAR MÉTODO DE PAGAMENTO
    // ========================================================================
    public AssinaturaResponseDTO atualizarMetodo(UUID assinaturaId, AssinaturaUpdateDTO dto) {

        Assinatura assinatura = assinaturaRepository.findById(assinaturaId)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada"));

        MetodoPagamento novoMetodo = metodoPagamentoRepository.findById(dto.metodoPagamentoId())
                .orElseThrow(() -> new EntityNotFoundException("Método de pagamento não encontrado"));

        if (!novoMetodo.getUsuario().getId().equals(assinatura.getUsuario().getId())) {
            throw new IllegalArgumentException("Este método de pagamento não pertence a este usuário");
        }

        assinatura.setMetodoPagamento(novoMetodo);
        assinaturaRepository.save(assinatura);

        return toResponse(assinatura);
    }

    // ========================================================================
    // RENOVAÇÃO AUTOMÁTICA (apenas exemplo)
    // ========================================================================
    /**
     * @Scheduled(cron = "0 0 3 * * *")
     * Executaria cada madrugada
     */
    public void renovarMensalmente() {
        List<Assinatura> assinaturas = assinaturaRepository.findByStatus("ATIVA");

        for (Assinatura a : assinaturas) {
            boolean cobrado = executarCobrancaMock(a.getMetodoPagamento());

            if (!cobrado) {
                a.setStatus("EM_ATRASO");
            }

            assinaturaRepository.save(a);
        }
    }

    // ========================================================================
    // HELPERS
    // ========================================================================
    private AssinaturaResponseDTO toResponse(Assinatura a) {
        return new AssinaturaResponseDTO(
                a.getId(),
                a.getUsuarioId().getClass(),
                a.getPlano().getClass(),
                a.getMetodoPagamento() != null ? a.getMetodoPagamento().getId() : null,
                a.getStatus(),
                a.getIniciadaEm(),
                a.getCanceladaEm()
        );
    }

    private boolean executarCobrancaMock(MetodoPagamento metodo) {
        try {
            String ultimos4 = metodo.getUltimos4();

            if (ultimos4 == null || ultimos4.isBlank())
                return false;

            char last = ultimos4.charAt(ultimos4.length() - 1);
            return last != '0';

        } catch (Exception e) {
            return false;
        }
    }
}
