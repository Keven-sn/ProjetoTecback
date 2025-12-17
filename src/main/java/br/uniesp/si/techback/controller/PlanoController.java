package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.plano.PlanoCreateDTO;
import br.uniesp.si.techback.dto.plano.PlanoResponseDTO;
import br.uniesp.si.techback.service.PlanoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService service;

    @PostMapping
    public PlanoResponseDTO criar(@RequestBody PlanoCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping
    public List<PlanoResponseDTO> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public PlanoResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @GetMapping("/codigo/{codigo}")
    public PlanoResponseDTO buscarPorCodigo(@PathVariable String codigo) {
        return service.buscarPorCodigo(codigo);
    }
}
