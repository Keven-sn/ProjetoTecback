package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.favorito.FavoritoCreateDTO;
import br.uniesp.si.techback.dto.favorito.FavoritoResponseDTO;
import br.uniesp.si.techback.service.FavoritoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/favoritos")
@RequiredArgsConstructor
public class FavoritoController {

    private final FavoritoService service;

    @PostMapping
    public FavoritoResponseDTO adicionar(@RequestBody FavoritoCreateDTO dto) {
        return service.adicionar(dto);
    }

    @DeleteMapping
    public void remover(
            @RequestParam UUID usuarioId,
            @RequestParam UUID conteudoId
    ) {
        service.remover(usuarioId, conteudoId);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<FavoritoResponseDTO> listar(@PathVariable UUID usuarioId) {
        return service.listar(usuarioId);
    }
}
