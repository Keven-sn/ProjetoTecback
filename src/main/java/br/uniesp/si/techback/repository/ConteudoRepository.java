package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Conteudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface ConteudoRepository extends JpaRepository<Conteudo, UUID> {

    List<Conteudo> findByGeneroIgnoreCase(String genero);

    List<Conteudo> findByTipo(String tipo);

    @Query("SELECT c FROM Conteudo c WHERE LOWER(c.titulo) LIKE LOWER(CONCAT('%', :q, '%'))")
    List<Conteudo> buscarPorPalavraChave(String q);

}
