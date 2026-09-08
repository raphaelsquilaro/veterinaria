package sp.senai.org.vetmark.dto.response;

import sp.senai.org.vetmark.model.entity.Pedido;
import sp.senai.org.vetmark.model.entity.Produto;
import sp.senai.org.vetmark.model.entity.Servico;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        Long id,
        Integer quantidade,
        BigDecimal valorUnitario,
        BigDecimal valorTotal,
        Pedido pedido,
        Produto produto
) {
}
