package sp.senai.org.vetmark.dto.request;

import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.model.entity.ItemPedido;
import sp.senai.org.vetmark.model.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoRequest(
        LocalDateTime dataPedido,
        StatusPedido status,
        BigDecimal valorTotal,
        Cliente cliente,
        List<ItemPedido> itens
) {
}
