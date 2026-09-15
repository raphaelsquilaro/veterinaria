package sp.senai.org.vetmark.dto.request;

import sp.senai.org.vetmark.model.enums.StatusPedido;

import java.util.List;

public record PedidoRequest(
        StatusPedido status,
        Long clienteId,
        List<ItemPedidoRequest> itens
) {
}
