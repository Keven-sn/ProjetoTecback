package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.metodo.MetodoPagamentoCreateDTO;
import br.uniesp.si.techback.dto.metodo.MetodoPagamentoResponseDTO;
import br.uniesp.si.techback.service.MetodoPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/metodos-pagamento")
@RequiredArgsConstructor
public class MetodoPagamentoController {

    private final MetodoPagamentoService service;

    @PostMapping
    public MetodoPagamentoResponseDTO criar(@RequestBody MetodoPagamentoCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<MetodoPagamentoResponseDTO> listar(@PathVariable UUID usuarioId) {
        return service.listar(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void remover(@PathVariable UUID id) {
        service.remover(id);
    }
}
