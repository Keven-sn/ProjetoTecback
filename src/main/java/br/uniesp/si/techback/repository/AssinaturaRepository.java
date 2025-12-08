package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Assinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {

    List<Assinatura> findByStatus(String status);

    List<Assinatura> findByUsuarioId(UUID usuarioId);

}
