package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.favorito.FavoritoCreateDTO;
import br.uniesp.si.techback.dto.favorito.FavoritoResponseDTO;
import br.uniesp.si.techback.service.FavoritoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    private final FavoritoService service;

    public FavoritoController(FavoritoService service) {
        this.service = service;
    }

    @PostMapping
    public FavoritoResponseDTO adicionar(@Valid @RequestBody FavoritoCreateDTO dto) {
        return service.adicionar(dto);
    }

    @DeleteMapping
    public void remover(
            @RequestParam UUID usuarioId,
            @RequestParam UUID conteudoId
    ) {
        service.remover(usuarioId, conteudoId);
    }

    @GetMapping("/{usuarioId}")
    public List<FavoritoResponseDTO> listar(@PathVariable UUID usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }
}
