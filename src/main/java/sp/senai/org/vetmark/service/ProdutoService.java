package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.ProdutoRequest;
import sp.senai.org.vetmark.dto.response.ProdutoResponse;
import sp.senai.org.vetmark.model.entity.Produto;
import sp.senai.org.vetmark.repository.ProdutoRepository;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoService(ProdutoRepository repository) {
        this.repository = repository;
    }

    // =========================
    // MÉTODOS DO THYMELEAF
    // =========================

    public List<Produto> findAll() {
        return repository.findAll();
    }

    public Produto findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Produto não encontrado"
                        )
                );
    }

    public Produto save(Produto produto) {
        return repository.save(produto);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    // =========================
    // MÉTODOS DA API
    // =========================

    public List<ProdutoResponse> listarApi() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public ProdutoResponse buscarApi(Long id) {

        Produto produto = findById(id);

        return toResponse(produto);
    }


    public ProdutoResponse criarApi(
            ProdutoRequest request
    ) {

        Produto produto = new Produto();

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setEstoque(request.estoque());
        produto.setAtivo(request.ativo());

        Produto salvo =
                repository.save(produto);

        return toResponse(salvo);
    }


    public ProdutoResponse atualizarApi(
            Long id,
            ProdutoRequest request
    ) {

        Produto produto = findById(id);

        produto.setNome(request.nome());
        produto.setDescricao(request.descricao());
        produto.setPreco(request.preco());
        produto.setEstoque(request.estoque());
        produto.setAtivo(request.ativo());

        Produto atualizado =
                repository.save(produto);

        return toResponse(atualizado);
    }


    public void excluirApi(Long id) {

        Produto produto = findById(id);

        repository.delete(produto);
    }


    // =========================
    // ENTITY → RESPONSE
    // =========================

    private ProdutoResponse toResponse(
            Produto produto
    ) {

        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getDescricao(),
                produto.getPreco(),
                produto.getEstoque(),
                produto.getAtivo()
        );
    }
}