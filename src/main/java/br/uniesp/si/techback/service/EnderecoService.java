package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.endereco.EnderecoDTO;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.repository.EnderecoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public Endereco salvar(EnderecoDTO dto) {
        Endereco e = new Endereco();
        e.setLogradouro(dto.logradouro());
        e.setNumero(dto.numero());
        e.setComplemento(dto.complemento());
        e.setBairro(dto.bairro());
        e.setCidade(dto.cidade());
        e.setEstado(dto.estado());
        e.setCep(dto.cep());
        return repository.save(e);
    }

    public Endereco buscar(UUID id) {
        return repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Endereço não encontrado"));
    }
}
