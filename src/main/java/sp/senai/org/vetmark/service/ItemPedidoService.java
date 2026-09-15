package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.response.ItemPedidoResponse;
import sp.senai.org.vetmark.model.entity.ItemPedido;
import sp.senai.org.vetmark.repository.ItemPedidoRepository;

import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository repository;

    public ItemPedidoService(ItemPedidoRepository repository) {
        this.repository = repository;
    }

    public List<ItemPedidoResponse> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ItemPedidoResponse findById(Long id) {

        ItemPedido itemPedido =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Item do pedido não encontrado"
                                )
                        );

        return toResponse(itemPedido);
    }

    public void delete(Long id) {

        ItemPedido itemPedido =
                repository.findById(id)
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Item do pedido não encontrado"
                                )
                        );

        repository.delete(itemPedido);
    }

    private ItemPedidoResponse toResponse(
            ItemPedido itemPedido
    ) {

        return new ItemPedidoResponse(
                itemPedido.getId(),
                itemPedido.getProduto() != null
                        ? itemPedido.getProduto().getId()
                        : null,
                itemPedido.getProduto() != null
                        ? itemPedido.getProduto().getNome()
                        : null,
                itemPedido.getQuantidade(),
                itemPedido.getValorUnitario(),
                itemPedido.getValorTotal()
        );
    }
}