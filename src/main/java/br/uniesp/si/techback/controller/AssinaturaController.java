package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.assinatura.AssinaturaCreateDTO;
import br.uniesp.si.techback.dto.assinatura.AssinaturaResponseDTO;
import br.uniesp.si.techback.model.enums.StatusAssinatura;
import br.uniesp.si.techback.service.AssinaturaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/assinaturas")
@RequiredArgsConstructor
public class AssinaturaController {

    private final AssinaturaService service;

    @PostMapping
    public AssinaturaResponseDTO criar(@RequestBody AssinaturaCreateDTO dto) {
        return service.criar(dto);
    }

    @PutMapping("/{id}/cancelar")
    public AssinaturaResponseDTO cancelar(@PathVariable UUID id) {
        return service.cancelar(id);
    }

    @GetMapping("/status/{status}")
    public List<AssinaturaResponseDTO> listarPorStatus(@PathVariable StatusAssinatura status) {
        return service.listarPorStatus(status);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<AssinaturaResponseDTO> listarPorUsuario(@PathVariable UUID usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }
}
