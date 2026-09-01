package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.MovimentacaoFinanceiraRequest;
import sp.senai.org.vetmark.dto.response.AgendamentoResponse;
import sp.senai.org.vetmark.dto.response.MovimentacaoFinanceiraResponse;
import sp.senai.org.vetmark.model.entity.MovimentacaoFinanceira;
import sp.senai.org.vetmark.repository.MovimentacaoFinanceiraRepository;

import java.util.List;

@Service
public class MovimentacaoFinanceiraService {

    private final MovimentacaoFinanceiraRepository repository;

    public MovimentacaoFinanceiraService(MovimentacaoFinanceiraRepository repository) {
        this.repository = repository;
    }

    public List<MovimentacaoFinanceiraResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public MovimentacaoFinanceiraResponse findById(Long id) {
        MovimentacaoFinanceira movimentacaoFinanceira = repository.findById(id)
                .orElseThrow();
        return toResponse(movimentacaoFinanceira);
    }

    public MovimentacaoFinanceiraResponse create(MovimentacaoFinanceiraRequest request) {
        MovimentacaoFinanceira movimentacaoFinanceira = new MovimentacaoFinanceira();

        movimentacaoFinanceira.setDescricao(request.descricao());
        movimentacaoFinanceira.setValor(request.valor());
        movimentacaoFinanceira.setTipo(request.tipo());
        movimentacaoFinanceira.setCategoria(request.categoria());
        movimentacaoFinanceira.setData(request.data());
        movimentacaoFinanceira.setPedido(request.pedido());

        MovimentacaoFinanceira savedMovimentacaoFinanceira = repository.save(movimentacaoFinanceira);

        return toResponse(savedMovimentacaoFinanceira);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private MovimentacaoFinanceiraResponse toResponse(MovimentacaoFinanceira movimentacaoFinanceira) {
        return new MovimentacaoFinanceiraResponse(
                movimentacaoFinanceira.getId(),
                movimentacaoFinanceira.getDescricao(),
                movimentacaoFinanceira.getValor(),
                movimentacaoFinanceira.getTipo(),
                movimentacaoFinanceira.getCategoria(),
                movimentacaoFinanceira.getData(),
                movimentacaoFinanceira.getPedido()
        );
    }
}
