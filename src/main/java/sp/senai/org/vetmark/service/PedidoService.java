package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.PedidoRequest;
import sp.senai.org.vetmark.dto.response.PedidoResponse;
import sp.senai.org.vetmark.model.entity.Pedido;
import sp.senai.org.vetmark.repository.PedidoRepository;

import java.util.List;

@Service
public class PedidoService {

    private final PedidoRepository repository;

    public PedidoService(PedidoRepository repository) {
        this.repository = repository;
    }

    public List<PedidoResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PedidoResponse findByAll(Long id) {
        Pedido pedido = repository.findById(id)
                .orElseThrow();
        return toResponse(pedido);
    }

    public PedidoResponse create(PedidoRequest request) {
        Pedido pedido = new Pedido();

        pedido.setDataPedido(request.dataPedido());
        pedido.setStatus(request.status());
        pedido.setValorTotal(request.valorTotal());
        pedido.setCliente(request.cliente());
        pedido.setItens(request.itens());

        Pedido savedPedido = repository.save(pedido);

        return toResponse(savedPedido);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PedidoResponse toResponse(Pedido pedido) {
        return new PedidoResponse(
                pedido.getId(),
                pedido.getDataPedido(),
                pedido.getStatus(),
                pedido.getValorTotal(),
                pedido.getCliente(),
                pedido.getItens()
        );
    }
}
