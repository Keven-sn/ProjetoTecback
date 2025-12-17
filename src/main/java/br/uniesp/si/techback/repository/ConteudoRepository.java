package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.model.enums.TipoConteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ConteudoRepository extends JpaRepository<Conteudo, UUID> {

    List<Conteudo> findAllByOrderByTituloAsc();

    @Query("""
        SELECT c FROM Conteudo c
        WHERE (:tipo IS NULL OR c.tipo = :tipo)
          AND (:genero IS NULL OR LOWER(c.genero) = LOWER(:genero))
          AND (:q IS NULL OR LOWER(c.titulo) LIKE LOWER(CONCAT('%', :q, '%')))
        ORDER BY c.titulo
    """)
    List<Conteudo> filtrar(TipoConteudo tipo, String genero, String q);
}