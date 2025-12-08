package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Conteudo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository
public interface ConteudoRepository extends JpaRepository<Conteudo, UUID> {

    List<Conteudo> findByGeneroIgnoreCaseOrderByTituloAsc(String genero);

    @Query("SELECT c FROM Conteudo c WHERE c.titulo ILIKE %:keyword% OR c.sinopse ILIKE %:keyword% ORDER BY c.relevancia DESC")
    List<Conteudo> buscarPorPalavraChave(String keyword);

    @Query("SELECT c FROM Conteudo c WHERE c.ano > :ano ORDER BY c.titulo ASC")
    List<Conteudo> lancadosApos(int ano);

    @Query("SELECT c FROM Conteudo c WHERE c.trailerUrl IS NOT NULL")
    List<Conteudo> comTrailer();

    Collection<Object> topPorRelevancia(PageRequest of);
}
