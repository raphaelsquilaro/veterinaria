package sp.senai.org.vetmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.Produto;
import sp.senai.org.vetmark.repository.ProdutoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProdutoService {

    private final ProdutoRepository repository;

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Produto não encontrado"
                        )
                );
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public void delete(Long id) {
        Produto produto = findById(id);
        repository.delete(produto);
    }
}