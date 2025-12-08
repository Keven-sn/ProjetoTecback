package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.conteudo.ConteudoCreateDTO;
import br.uniesp.si.techback.dto.conteudo.ConteudoResponseDTO;
import br.uniesp.si.techback.model.Conteudo;
import br.uniesp.si.techback.service.ConteudoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/conteudos")
public class ConteudoController {

    private final ConteudoService service;

    public ConteudoController(ConteudoService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<ConteudoResponseDTO>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConteudoResponseDTO> buscar(@PathVariable UUID id) {
        return ResponseEntity.ok(service.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ConteudoResponseDTO> criar(@Valid @RequestBody ConteudoCreateDTO dto) {
        Conteudo salvo = service.criar(dto);
        return ResponseEntity.ok(service.toResponse(salvo));
    }



    @PutMapping("/{id}")
    public ResponseEntity<Conteudo> atualizar(@PathVariable UUID id, @RequestBody Conteudo conteudo) {
        return ResponseEntity.ok(service.atualizar(id, conteudo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/search")
    public ResponseEntity<List<ConteudoResponseDTO>> buscarPorPalavra(@RequestParam String q) {
        return ResponseEntity.ok(service.buscarPorPalavra(q));
    }

    @GetMapping("/top")
    public ResponseEntity<List<ConteudoResponseDTO>> top(@RequestParam(defaultValue = "10") int n) {
        return ResponseEntity.ok(service.topNPorRelevancia(n));
    }
}
