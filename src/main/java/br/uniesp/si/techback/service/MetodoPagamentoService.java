package br.uniesp.si.techback.service;

import br.uniesp.si.techback.dto.metodo.*;
import br.uniesp.si.techback.model.MetodoPagamento;
import br.uniesp.si.techback.model.Usuario;
import br.uniesp.si.techback.repository.MetodoPagamentoRepository;
import br.uniesp.si.techback.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MetodoPagamentoService {

    private final MetodoPagamentoRepository repo;
    private final UsuarioRepository usuarioRepo;

    public MetodoPagamentoService(MetodoPagamentoRepository repo, UsuarioRepository usuarioRepo) {
        this.repo = repo;
        this.usuarioRepo = usuarioRepo;
    }

    public MetodoPagamentoResponseDTO criar(MetodoPagamentoCreateDTO dto) {

        Usuario usuario = usuarioRepo.findById(dto.usuarioId())
                .orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

        MetodoPagamento metodo = new MetodoPagamento();
        metodo.setUsuario(usuario);
        metodo.setTipo(dto.tipo());
        metodo.setApelido(dto.apelido());

        if (dto.tipo().equals("CARTAO")) {
            metodo.setBandeira(dto.bandeira());
            metodo.setExpiracao(dto.validade());

            if (dto.numeroCartao() == null || dto.numeroCartao().length() < 4)
                throw new IllegalArgumentException("Número do cartão inválido");

            String ultimos4 = dto.numeroCartao().substring(dto.numeroCartao().length() - 4);
            metodo.setNumeroMascarado("**** **** **** " + ultimos4);
        }

        if (dto.tipo().equals("PIX")) {
            if (dto.chavePix() == null)
                throw new IllegalArgumentException("Chave PIX obrigatória");
            metodo.setChavePix(dto.chavePix());
        }

        repo.save(metodo);
        return toResponse(metodo);
    }

    public List<MetodoPagamentoResponseDTO> listarPorUsuario(UUID userId) {
        return repo.findByUsuarioId(userId)
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MetodoPagamentoResponseDTO atualizar(UUID id, MetodoPagamentoUpdateDTO dto) {
        MetodoPagamento metodo = repo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Método não encontrado"));

        if (dto.apelido() != null) metodo.setApelido(dto.apelido());
        if (dto.ativo() != null) metodo.setAtivo(dto.ativo());

        repo.save(metodo);
        return toResponse(metodo);
    }

    public void deletar(UUID id) {
        if (!repo.existsById(id))
            throw new IllegalArgumentException("Método de pagamento não encontrado");

        repo.deleteById(id);
    }

    private MetodoPagamentoResponseDTO toResponse(MetodoPagamento m) {
        return new MetodoPagamentoResponseDTO(
                m.getId(),
                m.getTipo(),
                m.getApelido(),
                m.getBandeira(),
                m.getNumeroMascarado(),
                m.getExpiracao(),
                m.getChavePix(),
                m.isAtivo()
        );
    }
}
