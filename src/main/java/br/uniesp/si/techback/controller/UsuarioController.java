package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.usuario.UsuarioCreateDTO;
import br.uniesp.si.techback.dto.usuario.UsuarioResponseDTO;
import br.uniesp.si.techback.dto.usuario.UsuarioUpdateDTO;
import br.uniesp.si.techback.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listar() {
        return service.listar();
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(
            @PathVariable UUID id,
            @Valid @RequestBody UsuarioUpdateDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}
