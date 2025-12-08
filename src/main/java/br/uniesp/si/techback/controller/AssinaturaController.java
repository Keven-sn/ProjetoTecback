package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.assinatura.AssinaturaCreateDTO;
import br.uniesp.si.techback.dto.assinatura.AssinaturaResponseDTO;
import br.uniesp.si.techback.service.AssinaturaService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/assinaturas")
public class AssinaturaController {

    private final AssinaturaService service;

    public AssinaturaController(AssinaturaService service) {
        this.service = service;
    }

    @PostMapping
    public AssinaturaResponseDTO criar(@Valid @RequestBody AssinaturaCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/{id}")
    public AssinaturaResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @PostMapping("/{id}/cancelar")
    public AssinaturaResponseDTO cancelar(@PathVariable UUID id) {
        return service.cancelar(id);
    }

    @GetMapping("/status/{status}")
    public List<AssinaturaResponseDTO> listarPorStatus(@PathVariable String status) {
        return service.listarPorStatus(status);
    }
}
