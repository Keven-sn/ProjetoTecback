package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.conteudo.ConteudoCreateDTO;
import br.uniesp.si.techback.dto.conteudo.ConteudoResponseDTO;
import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.repository.ConteudoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ConteudoService {

    private final ConteudoRepository repository;

    public ConteudoService(ConteudoRepository repository) {
        this.repository = repository;
    }

    public ConteudoResponseDTO criar(ConteudoCreateDTO dto) {
        Conteudo c = new Conteudo();
        c.setTitulo(dto.titulo());
        c.setTipo(dto.tipo());
        c.setAno(dto.ano());
        c.setDuracaoMinutos(dto.duracaoMinutos());
        c.setRelevancia(dto.relevancia());
        c.setSinopse(dto.sinopse());
        c.setGenero(dto.genero());
        c.setTrailerUrl(dto.trailerUrl());

        return toResponse(repository.save(c));
    }

    public ConteudoResponseDTO buscar(UUID id) {
        Conteudo c = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conteúdo não encontrado"));
        return toResponse(c);
    }

    public List<ConteudoResponseDTO> listar() {
        return repository.findAll()
                .stream().map(this::toResponse).toList();
    }

    private ConteudoResponseDTO toResponse(Conteudo c) {
        return new ConteudoResponseDTO(
                c.getId(),
                c.getTitulo(),
                c.getTipo(),
                c.getGenero(),
                c.getAno(),
                c.getRelevancia()
        );
    }
}
