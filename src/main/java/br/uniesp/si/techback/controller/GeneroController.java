package br.uniesp.si.techback.controller;

import br.uniesp.si.techback.model.Genero;
import br.uniesp.si.techback.repository.GeneroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/generos")
public class GeneroController {

    private final GeneroRepository repository;

    public GeneroController(GeneroRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Genero> listar() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Genero buscar(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping
    public Genero criar(@RequestBody Genero genero) {
        return repository.save(genero);
    }

    @PutMapping("/{id}")
    public Genero atualizar(@PathVariable Long id, @RequestBody Genero genero) {
        genero.setId(id);
        return repository.save(genero);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
