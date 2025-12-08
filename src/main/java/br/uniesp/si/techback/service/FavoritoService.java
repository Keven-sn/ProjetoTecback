package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.favorito.FavoritoCreateDTO;
import br.uniesp.si.techback.dto.favorito.FavoritoResponseDTO;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.FavoritoId;
import br.uniesp.si.techback.repository.ConteudoRepository;
import br.uniesp.si.techback.repository.FavoritoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FavoritoService {

    private final FavoritoRepository favoritoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;

    public FavoritoService(FavoritoRepository favoritoRepository,
                           UsuarioRepository usuarioRepository,
                           ConteudoRepository conteudoRepository) {
        this.favoritoRepository = favoritoRepository;
        this.usuarioRepository = usuarioRepository;
        this.conteudoRepository = conteudoRepository;
    }

    public FavoritoResponseDTO adicionar(FavoritoCreateDTO dto) {
        UUID usuarioId = dto.usuarioId();
        UUID conteudoId = dto.conteudoId();

        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        if (!conteudoRepository.existsById(conteudoId)) {
            throw new EntityNotFoundException("Conteúdo não encontrado");
        }

        if (favoritoRepository.existsByIdUsuarioIdAndIdConteudoId(usuarioId, conteudoId)) {
            throw new IllegalArgumentException("Conteúdo já favoritado por esse usuário");
        }

        FavoritoId id = new FavoritoId(usuarioId, conteudoId);
        Favorito f = new Favorito();
        f.setId(id);
        f.setCriadoEm(LocalDateTime.now());

        Favorito salvo = favoritoRepository.save(f);

        return new FavoritoResponseDTO(salvo.getId().getUsuarioId(), salvo.getId().getConteudoId(), salvo.getCriadoEm());
    }

    public void remover(FavoritoCreateDTO dto) {
        UUID usuarioId = dto.usuarioId();
        UUID conteudoId = dto.conteudoId();

        if (!favoritoRepository.existsByIdUsuarioIdAndIdConteudoId(usuarioId, conteudoId)) {
            throw new EntityNotFoundException("Favorito não encontrado");
        }

        favoritoRepository.deleteByIdUsuarioIdAndIdConteudoId(usuarioId, conteudoId);
    }

    @Transactional(readOnly = true)
    public List<FavoritoResponseDTO> listarPorUsuario(UUID usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }

        return favoritoRepository.findByIdUsuarioIdOrderByCriadoEmDesc(usuarioId)
                .stream()
                .map(f -> new FavoritoResponseDTO(f.getId().getUsuarioId(), f.getId().getConteudoId(), f.getCriadoEm()))
                .toList();
    }
}
