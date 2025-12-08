package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.endereco.EnderecoCreateDTO;
import br.uniesp.si.techback.dto.endereco.EnderecoResponseDTO;
import br.uniesp.si.techback.dto.endereco.EnderecoUpdateDTO;
import br.uniesp.si.techback.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public EnderecoResponseDTO criar(@Valid @RequestBody EnderecoCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/{id}")
    public EnderecoResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PutMapping("/{id}")
    public EnderecoResponseDTO atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody EnderecoUpdateDTO dto
    ) {
        return service.atualizar(id, dto);
    }
}
