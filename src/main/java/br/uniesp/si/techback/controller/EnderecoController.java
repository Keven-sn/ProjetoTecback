package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.dto.EnderecoDTO;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.service.EnderecoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    private final EnderecoService service;

    public EnderecoController(EnderecoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Endereco> criar(@Valid @RequestBody EnderecoDTO dto) {
        Endereco salvo = service.salvar(dto);
        return ResponseEntity.created(URI.create("/api/enderecos/" + salvo.getId())).body(salvo);
    }
}
