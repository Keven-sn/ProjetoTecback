package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.plano.PlanoCreateDTO;
import br.uniesp.si.techback.dto.plano.PlanoResponseDTO;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlanoService {

    private final PlanoRepository repository;

    public PlanoService(PlanoRepository repository) {
        this.repository = repository;
    }

    public PlanoResponseDTO criar(PlanoCreateDTO dto) {
        Plano p = new Plano();
        p.setNome(dto.nome());
        p.setDescricao(dto.descricao());
        p.setValorMensal(dto.valorMensal());
        p.setResolucao(dto.resolucao());
        p.setDispositivos(dto.dispositivos());

        return toResponse(repository.save(p));
    }

    public PlanoResponseDTO buscar(UUID id) {
        Plano p = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Plano não encontrado"));
        return toResponse(p);
    }

    public List<PlanoResponseDTO> listar() {
        return repository.findAll().stream().map(this::toResponse).toList();
    }

    private PlanoResponseDTO toResponse(Plano p) {
        return new PlanoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getValorMensal(),
                p.getResolucao(),
                p.getDispositivos()
        );
    }
}
