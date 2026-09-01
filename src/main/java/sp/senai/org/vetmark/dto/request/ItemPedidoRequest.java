package sp.senai.org.vetmark.dto.request;

import sp.senai.org.vetmark.model.entity.Pedido;
import sp.senai.org.vetmark.model.entity.Servico;

import java.math.BigDecimal;

public record ItemPedidoRequest(
        Integer quantidade,
        BigDecimal valorUnitario,
        BigDecimal valorTotal,
        Pedido pedido,
        Servico servico
) {
}
