package br.uniesp.si.techback.repository;

import br.uniesp.si.techback.model.Assinatura;
import br.uniesp.si.techback.model.enums.StatusAssinatura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface AssinaturaRepository extends JpaRepository<Assinatura, UUID> {

    List<Assinatura> findByUsuarioId(UUID usuarioId);

    List<Assinatura> findByStatus(StatusAssinatura status);
}
