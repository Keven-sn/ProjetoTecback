package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.metodo.*;
import br.uniesp.si.techback.service.MetodoPagamentoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/metodos-pagamento")
public class MetodoPagamentoController {

    private final MetodoPagamentoService service;

    public MetodoPagamentoController(MetodoPagamentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<MetodoPagamentoResponseDTO> criar(
            @Valid @RequestBody MetodoPagamentoCreateDTO dto
    ) {
        return ResponseEntity.ok(service.criar(dto));
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<MetodoPagamentoResponseDTO>> listarPorUsuario(
            @PathVariable UUID usuarioId
    ) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MetodoPagamentoResponseDTO> atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody MetodoPagamentoUpdateDTO dto
    ) {
        return ResponseEntity.ok(service.atualizar(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable UUID id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
