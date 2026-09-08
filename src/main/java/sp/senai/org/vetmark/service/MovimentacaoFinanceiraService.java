package sp.senai.org.vetmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.MovimentacaoFinanceira;
import sp.senai.org.vetmark.repository.MovimentacaoFinanceiraRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovimentacaoFinanceiraService {

    private final MovimentacaoFinanceiraRepository repository;

    public List<MovimentacaoFinanceira> findAll() {
        return repository.findAll();
    }

    public MovimentacaoFinanceira findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Movimentação financeira não encontrada"
                        )
                );
    }

    public MovimentacaoFinanceira save(
            MovimentacaoFinanceira movimentacao
    ) {
        return repository.save(movimentacao);
    }

    public void delete(Long id) {
        MovimentacaoFinanceira movimentacao = findById(id);
        repository.delete(movimentacao);
    }
}