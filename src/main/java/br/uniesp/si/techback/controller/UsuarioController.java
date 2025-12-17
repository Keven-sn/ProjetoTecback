package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.usuario.UsuarioCreateDTO;
import br.uniesp.si.techback.dto.usuario.UsuarioResponseDTO;
import br.uniesp.si.techback.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @PostMapping
    public UsuarioResponseDTO criar(@RequestBody UsuarioCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscar(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }
}
