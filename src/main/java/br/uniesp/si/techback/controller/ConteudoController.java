package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.conteudo.ConteudoCreateDTO;
import br.uniesp.si.techback.dto.conteudo.ConteudoResponseDTO;
import br.uniesp.si.techback.model.enums.TipoConteudo;
import br.uniesp.si.techback.service.ConteudoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/conteudos")
@RequiredArgsConstructor
public class ConteudoController {

    private final ConteudoService service;

    @GetMapping
    public List<ConteudoResponseDTO> listar(
            @RequestParam(required = false) TipoConteudo tipo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false) String q
    ) {
        return service.listar(tipo, genero, q);
    }

    @GetMapping("/{id}")
    public ConteudoResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PostMapping
    public ConteudoResponseDTO criar(@RequestBody ConteudoCreateDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}")
    public ConteudoResponseDTO atualizar(
            @PathVariable UUID id,
            @RequestBody ConteudoCreateDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }
}
