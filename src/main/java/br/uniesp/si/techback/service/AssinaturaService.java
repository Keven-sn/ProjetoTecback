package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.assinatura.AssinaturaCreateDTO;
import br.uniesp.si.techback.dto.assinatura.AssinaturaResponseDTO;
import br.uniesp.si.techback.model.Assinatura;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.model.enums.StatusAssinatura;
import br.uniesp.si.techback.repository.AssinaturaRepository;
import br.uniesp.si.techback.repository.PlanoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class AssinaturaService {

    private final AssinaturaRepository assinaturaRepository;
    private final UsuarioRepository usuarioRepository;
    private final PlanoRepository planoRepository;

    // ============================
    // CRIAR
    // ============================
    public AssinaturaResponseDTO criar(AssinaturaCreateDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Plano plano = planoRepository.findById(dto.planoId())
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));

        boolean possuiAtiva = assinaturaRepository.findByUsuarioId(usuario.getId())
                .stream()
                .anyMatch(a -> a.getStatus() == StatusAssinatura.ATIVA);

        if (possuiAtiva) {
            throw new IllegalArgumentException("Usuário já possui assinatura ativa");
        }

        Assinatura assinatura = new Assinatura();
        assinatura.setUsuario(usuario);
        assinatura.setPlano(plano);
        assinatura.setStatus(StatusAssinatura.ATIVA);
        assinatura.setIniciadaEm(LocalDateTime.now());

        return toResponse(assinaturaRepository.save(assinatura));
    }

    // ============================
    // CANCELAR
    // ============================
    public AssinaturaResponseDTO cancelar(UUID id) {

        Assinatura assinatura = assinaturaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Assinatura não encontrada"));

        assinatura.setStatus(StatusAssinatura.CANCELADA);
        assinatura.setCanceladaEm(LocalDateTime.now());

        return toResponse(assinaturaRepository.save(assinatura));
    }

    // ============================
    // LISTAR POR STATUS
    // ============================
    public List<AssinaturaResponseDTO> listarPorStatus(StatusAssinatura status) {
        return assinaturaRepository.findByStatus(status)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ============================
    // LISTAR POR USUÁRIO
    // ============================
    public List<AssinaturaResponseDTO> listarPorUsuario(UUID usuarioId) {
        return assinaturaRepository.findByUsuarioId(usuarioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ============================
    // MAPPER
    // ============================
    private AssinaturaResponseDTO toResponse(Assinatura a) {
        return new AssinaturaResponseDTO(
                a.getId(),
                a.getUsuario().getId(),
                a.getPlano().getId(),
                a.getStatus(),
                a.getIniciadaEm(),
                a.getCanceladaEm()
        );
    }
}
