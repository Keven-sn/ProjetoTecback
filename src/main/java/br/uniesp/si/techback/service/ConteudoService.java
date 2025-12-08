package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.conteudo.ConteudoCreateDTO;
import br.uniesp.si.techback.dto.conteudo.ConteudoResponseDTO;
import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.repository.ConteudoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConteudoService {

    private final ConteudoRepository repository;

    public ConteudoService(ConteudoRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ConteudoResponseDTO> listarTodos() {
        return repository.findAll().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ConteudoResponseDTO buscarPorId(UUID id) {
        Conteudo c = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conteúdo não encontrado"));
        return toResponse(c);
    }

    @Transactional
    public Conteudo criar(ConteudoCreateDTO dto) {
        Conteudo c = fromCreateDto(dto);
        return repository.save(c);
    }

    @Transactional
    public ConteudoResponseDTO criarRetornandoDTO(ConteudoCreateDTO dto) {
        Conteudo salvo = criar(dto);
        return toResponse(salvo);
    }

    @Transactional
    public Conteudo atualizar(UUID id, Conteudo dto) {
        Conteudo atual = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Conteúdo não encontrado"));
        atual.setTitulo(dto.titulo());
        atual.setTipo(dto.tipo());
        atual.setAno(dto.ano());
        atual.setDuracaoMinutos(dto.duracaoMinutos());
        atual.setRelevancia(dto.relevancia());
        atual.setSinopse(dto.sinopse());
        atual.setTrailerUrl(dto.trailerUrl());
        atual.setGenero(dto.genero());
        return repository.save(atual);
    }

    @Transactional
    public void deletar(UUID id) {
        if (!repository.existsById(id)) throw new EntityNotFoundException("Conteúdo não encontrado");
        repository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<ConteudoResponseDTO> buscarPorPalavra(String keyword) {
        return repository.buscarPorPalavraChave(keyword).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConteudoResponseDTO> topNPorRelevancia(int n) {
        return repository.topPorRelevancia(PageRequest.of(0, n)).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConteudoResponseDTO> lancadosApos(int ano) {
        return repository.lancadosApos(ano).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConteudoResponseDTO> porGenero(String genero) {
        return repository.findByGeneroIgnoreCaseOrderByTituloAsc(genero).stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<ConteudoResponseDTO> comTrailer() {
        return repository.comTrailer().stream()
                .map(this::toResponse)
                .collect(Collectors.toList());
    }

    /* -------------------
       MAPPERS
       ------------------- */

    public ConteudoResponseDTO toResponse(Conteudo c) {
        return new ConteudoResponseDTO(
                c.getId(),
                c.getTitulo(),
                c.getTipo(),
                c.getAno(),
                c.getDuracaoMinutos(),
                c.getRelevancia(),
                c.getSinopse(),
                c.getTrailerUrl(),
                c.getGenero()
        );
    }

    public Conteudo fromCreateDto(ConteudoCreateDTO dto) {
        return Conteudo.builder()
                .titulo(dto.titulo())
                .tipo(dto.tipo())
                .ano(dto.ano())
                .duracaoMinutos(dto.duracaoMinutos())
                .relevancia(dto.relevancia())
                .sinopse(dto.sinopse())
                .trailerUrl(dto.trailerUrl())
                .genero(dto.genero())
                .build();
    }
}
