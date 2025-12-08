package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.EnderecoDTO;
import br.uniesp.si.techback.model.Endereco;
import br.uniesp.si.techback.repository.EnderecoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EnderecoService {

    private final EnderecoRepository repository;

    public EnderecoService(EnderecoRepository repository) {
        this.repository = repository;
    }

    public Endereco salvar(EnderecoDTO dto) {
        if (dto == null) return null;
        Endereco e = Endereco.builder()
                .logradouro(dto.logradouro())
                .numero(dto.numero())
                .complemento(dto.complemento())
                .bairro(dto.bairro())
                .cidade(dto.cidade())
                .estado(dto.estado())
                .cep(dto.cep())
                .build();
        return repository.save(e);
    }
}
