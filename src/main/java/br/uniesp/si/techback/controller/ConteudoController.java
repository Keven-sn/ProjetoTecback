package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.conteudo.ConteudoCreateDTO;
import br.uniesp.si.techback.dto.conteudo.ConteudoResponseDTO;
import br.uniesp.si.techback.dto.conteudo.ConteudoUpdateDTO;
import br.uniesp.si.techback.service.ConteudoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/conteudos")
public class ConteudoController {

    private final ConteudoService service;

    public ConteudoController(ConteudoService service) {
        this.service = service;
    }

    @PostMapping
    public ConteudoResponseDTO criar(@Valid @RequestBody ConteudoCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<ConteudoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public ConteudoResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public ConteudoResponseDTO atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody ConteudoUpdateDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
