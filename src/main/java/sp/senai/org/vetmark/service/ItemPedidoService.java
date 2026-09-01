package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.ItemPedidoRequest;
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
        ItemPedido itemPedido = repository.findById(id)
                .orElseThrow();
        return toResponse(itemPedido);
    }

    public ItemPedidoResponse create(ItemPedidoRequest request) {
        ItemPedido itemPedido = new ItemPedido();

        itemPedido.setQuantidade(request.quantidade());
        itemPedido.setValorUnitario(request.valorUnitario());
        itemPedido.setValorTotal(request.valorTotal());
        itemPedido.setPedido(request.pedido());
        itemPedido.setServico(request.servico());

        ItemPedido savedItemPedido = repository.save(itemPedido);

        return toResponse(savedItemPedido);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ItemPedidoResponse toResponse(ItemPedido itemPedido) {
        return new ItemPedidoResponse(
                itemPedido.getId(),
                itemPedido.getQuantidade(),
                itemPedido.getValorUnitario(),
                itemPedido.getValorTotal(),
                itemPedido.getPedido(),
                itemPedido.getServico()
        );
    }
}
