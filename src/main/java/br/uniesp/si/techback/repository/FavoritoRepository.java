package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Favorito;
import br.uniesp.si.techback.model.FavoritoId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface FavoritoRepository extends JpaRepository<Favorito, FavoritoId> {
    List<Favorito> findByIdUsuarioIdOrderByCriadoEmDesc(UUID usuarioId);
    boolean existsByIdUsuarioIdAndIdConteudoId(UUID usuarioId, UUID conteudoId);
    void deleteByIdUsuarioIdAndIdConteudoId(UUID usuarioId, UUID conteudoId);
}
