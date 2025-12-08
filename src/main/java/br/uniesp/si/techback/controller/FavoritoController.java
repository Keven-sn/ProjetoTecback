package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.favorito.FavoritoCreateDTO;
import br.uniesp.si.techback.dto.favorito.FavoritoResponseDTO;
import br.uniesp.si.techback.service.FavoritoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favoritos")
public class FavoritoController {

    private final FavoritoService service;

    public FavoritoController(FavoritoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<FavoritoResponseDTO> adicionar(@Valid @RequestBody FavoritoCreateDTO dto) {
        FavoritoResponseDTO criado = service.adicionar(dto);
        return ResponseEntity.created(URI.create("/api/favoritos/" + criado.usuarioId() + "/" + criado.conteudoId())).body(criado);
    }

    @DeleteMapping
    public ResponseEntity<Void> remover(@Valid @RequestBody FavoritoCreateDTO dto) {
        service.remover(dto);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<FavoritoResponseDTO>> listarPorUsuario(@PathVariable UUID usuarioId) {
        return ResponseEntity.ok(service.listarPorUsuario(usuarioId));
    }
}
