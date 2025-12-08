package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.favorito.FavoritoResponseDTO;
import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.repository.ConteudoRepository;
import br.uniesp.si.techback.repository.FavoritoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class FavoritoService {

    private final FavoritoRepository repository;
    private final UsuarioRepository usuarioRepository;
    private final ConteudoRepository conteudoRepository;

    public FavoritoService(FavoritoRepository repository,
                           UsuarioRepository usuarioRepository,
                           ConteudoRepository conteudoRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
        this.conteudoRepository = conteudoRepository;
    }

    public FavoritoResponseDTO adicionar(UUID usuarioId, UUID conteudoId) {

        if (!usuarioRepository.existsById(usuarioId))
            throw new EntityNotFoundException("Usuário não encontrado");

        if (!conteudoRepository.existsById(conteudoId))
            throw new EntityNotFoundException("Conteúdo não encontrado");

        Favorito fav = new Favorito(usuarioId, conteudoId, LocalDateTime.now());
        return toResponse(repository.save(fav));
    }

    public void remover(UUID usuarioId, UUID conteudoId) {
        repository.deleteByIdUsuarioIdAndConteudoId(usuarioId, conteudoId);
    }

    public List<FavoritoResponseDTO> listar(UUID usuarioId) {
        return repository.findByUsuarioId(usuarioId)
                .stream().map(this::toResponse).toList();
    }

    private FavoritoResponseDTO toResponse(Favorito f) {
        return new FavoritoResponseDTO(
                f.getUsuarioId(),
                f.getConteudoId(),
                f.getCriadoEm()
        );
    }
}
