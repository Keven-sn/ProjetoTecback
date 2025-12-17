package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.plano.PlanoCreateDTO;
import br.uniesp.si.techback.dto.plano.PlanoResponseDTO;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PlanoService {

    private final PlanoRepository repository;

    // ============================
    // CRIAR
    // ============================
    public PlanoResponseDTO criar(PlanoCreateDTO dto) {

        // limiteDiario:
        // null = ilimitado
        // >= 0 = limite definido
        if (dto.limiteDiario() != null && dto.limiteDiario() < 0) {
            throw new IllegalArgumentException("Limite diário não pode ser negativo");
        }

        if (dto.streamsSimultaneos() <= 0) {
            throw new IllegalArgumentException("Streams simultâneos deve ser maior que zero");
        }

        Plano plano = new Plano();
        plano.setCodigo(dto.codigo());
        plano.setLimiteDiario(dto.limiteDiario()); // null = ilimitado
        plano.setStreamsSimultaneos(dto.streamsSimultaneos());

        return toResponse(repository.save(plano));
    }

    // ============================
    // BUSCAR POR ID
    // ============================
    public PlanoResponseDTO buscar(UUID id) {
        Plano plano = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));
        return toResponse(plano);
    }

    // ============================
    // LISTAR
    // ============================
    public List<PlanoResponseDTO> listar() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // ============================
    // BUSCAR POR CÓDIGO
    // ============================
    public PlanoResponseDTO buscarPorCodigo(String codigo) {
        Plano plano = repository.findByCodigo(codigo)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));
        return toResponse(plano);
    }

    // ============================
    // MAPPER
    // ============================
    private PlanoResponseDTO toResponse(Plano p) {
        return new PlanoResponseDTO(
                p.getId(),
                p.getCodigo(),
                p.getLimiteDiario(),
                p.getStreamsSimultaneos()
        );
    }
}
