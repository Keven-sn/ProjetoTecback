package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.endereco.EnderecoCreateDTO;
import br.uniesp.si.techback.dto.endereco.EnderecoResponseDTO;
import br.uniesp.si.techback.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    @PostMapping
    public EnderecoResponseDTO criar(@RequestBody EnderecoCreateDTO dto) {
        return service.criar(dto);
    }

    @GetMapping("/{id}")
    public EnderecoResponseDTO buscar(@PathVariable UUID id) {
        return service.buscar(id);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<EnderecoResponseDTO> listarPorUsuario(@PathVariable UUID usuarioId) {
        return service.listarPorUsuario(usuarioId);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable UUID id) {
        service.excluir(id);
    }
}
