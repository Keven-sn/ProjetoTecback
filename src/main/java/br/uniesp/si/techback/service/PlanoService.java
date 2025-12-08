package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.plano.*;
import br.uniesp.si.techback.model.Plano;
import br.uniesp.si.techback.repository.PlanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PlanoService {

    private final PlanoRepository repo;

    public PlanoService(PlanoRepository repo) {
        this.repo = repo;
    }

    public PlanoResponseDTO criar(PlanoCreateDTO dto) {

        if (repo.existsByNomeIgnoreCase(dto.nome())) {
            throw new IllegalArgumentException("Já existe um plano com este nome.");
        }

        Plano plano = Plano.builder()
                .nome(dto.nome())
                .descricao(dto.descricao())
                .valorMensal(dto.valorMensal())
                .resolucao(dto.resolucao())
                .dispositivos(dto.dispositivos())
                .build();

        repo.save(plano);

        return toResponse(plano);
    }

    public List<PlanoResponseDTO> listarTodos() {
        return repo.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PlanoResponseDTO buscarPorId(UUID id) {
        Plano plano = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));
        return toResponse(plano);
    }

    public PlanoResponseDTO atualizar(UUID id, PlanoUpdateDTO dto) {
        Plano plano = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Plano não encontrado"));

        if (dto.nome() != null) plano.setNome(dto.nome());
        if (dto.descricao() != null) plano.setDescricao(dto.descricao());
        if (dto.valorMensal() != null) plano.setValorMensal(dto.valorMensal());
        if (dto.resolucao() != null) plano.setResolucao(dto.resolucao());
        if (dto.dispositivos() != null) plano.setDispositivos(dto.dispositivos());

        repo.save(plano);
        return toResponse(plano);
    }

    public void deletar(UUID id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("Plano não encontrado");
        }
        repo.deleteById(id);
    }


    private PlanoResponseDTO toResponse(Plano p) {
        return new PlanoResponseDTO(
                p.getId(),
                p.getNome(),
                p.getDescricao(),
                p.getValorMensal(),
                p.getResolucao(),
                p.getDispositivos()
        );
    }
}
