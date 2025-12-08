package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.plano.PlanoCreateDTO;
import br.uniesp.si.techback.dto.plano.PlanoResponseDTO;
import br.uniesp.si.techback.dto.plano.PlanoUpdateDTO;
import br.uniesp.si.techback.service.PlanoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/planos")
public class PlanoController {

    private final PlanoService service;

    public PlanoController(PlanoService service) {
        this.service = service;
    }

    @PostMapping
    public PlanoResponseDTO criar(@Valid @RequestBody PlanoCreateDTO dto) {
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

    @PutMapping("/{id}")
    public PlanoResponseDTO atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody PlanoUpdateDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
