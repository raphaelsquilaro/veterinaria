package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.ItemPedidoRequest;
import sp.senai.org.vetmark.dto.request.PedidoRequest;
import sp.senai.org.vetmark.dto.response.ItemPedidoResponse;
import sp.senai.org.vetmark.dto.response.PedidoResponse;
import sp.senai.org.vetmark.model.entity.ItemPedido;
import sp.senai.org.vetmark.model.entity.Pedido;
import sp.senai.org.vetmark.model.entity.Produto;
import sp.senai.org.vetmark.repository.ClienteRepository;
import sp.senai.org.vetmark.repository.PedidoRepository;
import sp.senai.org.vetmark.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;

    public PedidoService(
            PedidoRepository repository,
            ClienteRepository clienteRepository,
            ProdutoRepository produtoRepository
    ) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.produtoRepository = produtoRepository;
    }

    // =========================
    // MÉTODOS DO THYMELEAF
    // =========================

    public List<Pedido> findAll() {
        return repository.findAll();
    }

    public Pedido findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Pedido não encontrado"
                        )
                );
    }

    public Pedido save(Pedido pedido) {
        return repository.save(pedido);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    // =========================
    // MÉTODOS DA API
    // =========================

    public List<PedidoResponse> listarApi() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public PedidoResponse buscarApi(Long id) {

        Pedido pedido = findById(id);

        return toResponse(pedido);
    }


    public PedidoResponse criarApi(
            PedidoRequest request
    ) {

        Pedido pedido = new Pedido();

        pedido.setDataPedido(LocalDateTime.now());

        pedido.setStatus(request.status());

        pedido.setCliente(
                clienteRepository.findById(
                                request.clienteId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cliente não encontrado"
                                )
                        )
        );

        pedido.setItens(new ArrayList<>());

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoRequest itemRequest : request.itens()) {

            Produto produto =
                    produtoRepository.findById(
                                    itemRequest.produtoId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Produto não encontrado"
                                    )
                            );

            BigDecimal valorUnitario =
                    produto.getPreco();

            BigDecimal valorItem =
                    valorUnitario.multiply(
                            BigDecimal.valueOf(
                                    itemRequest.quantidade()
                            )
                    );

            ItemPedido item = new ItemPedido();

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemRequest.quantidade());
            item.setValorUnitario(valorUnitario);
            item.setValorTotal(valorItem);

            pedido.getItens().add(item);

            valorTotal =
                    valorTotal.add(valorItem);
        }

        pedido.setValorTotal(valorTotal);

        Pedido salvo =
                repository.save(pedido);

        return toResponse(salvo);
    }


    public PedidoResponse atualizarApi(
            Long id,
            PedidoRequest request
    ) {

        Pedido pedido = findById(id);

        pedido.setStatus(request.status());

        pedido.setCliente(
                clienteRepository.findById(
                                request.clienteId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cliente não encontrado"
                                )
                        )
        );

        pedido.getItens().clear();

        BigDecimal valorTotal = BigDecimal.ZERO;

        for (ItemPedidoRequest itemRequest : request.itens()) {

            Produto produto =
                    produtoRepository.findById(
                                    itemRequest.produtoId()
                            )
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Produto não encontrado"
                                    )
                            );

            BigDecimal valorUnitario =
                    produto.getPreco();

            BigDecimal valorItem =
                    valorUnitario.multiply(
                            BigDecimal.valueOf(
                                    itemRequest.quantidade()
                            )
                    );

            ItemPedido item = new ItemPedido();

            item.setPedido(pedido);
            item.setProduto(produto);
            item.setQuantidade(itemRequest.quantidade());
            item.setValorUnitario(valorUnitario);
            item.setValorTotal(valorItem);

            pedido.getItens().add(item);

            valorTotal =
                    valorTotal.add(valorItem);
        }

        pedido.setValorTotal(valorTotal);

        Pedido atualizado =
                repository.save(pedido);

        return toResponse(atualizado);
    }


    public void excluirApi(Long id) {

        Pedido pedido = findById(id);

        repository.delete(pedido);
    }


    // =========================
    // ENTITY → RESPONSE
    // =========================

    private PedidoResponse toResponse(
            Pedido pedido
    ) {

        List<ItemPedidoResponse> itens =
                pedido.getItens()
                        .stream()
                        .map(this::toItemResponse)
                        .toList();

        return new PedidoResponse(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getValorTotal(),

                pedido.getCliente() != null
                        ? pedido.getCliente().getId()
                        : null,

                pedido.getCliente() != null
                        ? pedido.getCliente().getNome()
                        : null,

                itens
        );
    }


    private ItemPedidoResponse toItemResponse(
            ItemPedido item
    ) {

        return new ItemPedidoResponse(
                item.getId(),

                item.getProduto() != null
                        ? item.getProduto().getId()
                        : null,

                item.getProduto() != null
                        ? item.getProduto().getNome()
                        : null,

                item.getQuantidade(),
                item.getValorUnitario(),
                item.getValorTotal()
        );
    }
}