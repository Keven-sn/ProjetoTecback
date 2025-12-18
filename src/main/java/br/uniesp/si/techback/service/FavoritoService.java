package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.favorito.FavoritoCreateDTO;
import br.uniesp.si.techback.dto.favorito.FavoritoResponseDTO;
import br.uniesp.si.techback.model.*;
import br.uniesp.si.techback.repository.ConteudoRepository;
import br.uniesp.si.techback.repository.FavoritoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;


    // ADICIONAR

    public FavoritoResponseDTO adicionar(FavoritoCreateDTO dto) {

        Usuario usuario = usuarioRepository.findById(dto.usuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));

        Conteudo conteudo = conteudoRepository.findById(dto.conteudoId())
                .orElseThrow(() -> new EntityNotFoundException("Conteúdo não encontrado"));

        FavoritoId id = new FavoritoId(dto.usuarioId(), dto.conteudoId());

        if (favoritoRepository.existsById(id)) {
            throw new IllegalArgumentException("Conteúdo já está nos favoritos");
        }

        Favorito favorito = new Favorito();
        favorito.setId(id);
        favorito.setUsuario(usuario);
        favorito.setConteudo(conteudo);
        favorito.setCriadoEm(LocalDateTime.now());

        return toResponse(favoritoRepository.save(favorito));
    }


    // REMOVER

    public void remover(UUID usuarioId, UUID conteudoId) {
        FavoritoId id = new FavoritoId(usuarioId, conteudoId);
        favoritoRepository.deleteById(id);
    }


    // LISTAR POR USUÁRIO

    public List<FavoritoResponseDTO> listar(UUID usuarioId) {
        return favoritoRepository.findByUsuarioIdOrderByCriadoEmDesc(usuarioId)
                .stream()
                .map(this::toResponse)
                .toList();
    }


    // MAPEAR

    private FavoritoResponseDTO toResponse(Favorito f) {
        return new FavoritoResponseDTO(
                f.getUsuario().getId(),
                f.getConteudo().getId(),
                f.getCriadoEm()
        );
    }
}
