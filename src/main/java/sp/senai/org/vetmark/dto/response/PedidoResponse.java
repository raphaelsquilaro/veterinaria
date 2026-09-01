package sp.senai.org.vetmark.dto.response;

import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.model.entity.ItemPedido;
import sp.senai.org.vetmark.model.enums.StatusPedido;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record PedidoResponse(
        Long id,
        LocalDateTime dataPedido,
        StatusPedido status,
        BigDecimal valorTotal,
        Cliente cliente,
        List<ItemPedido> itens
) {
}
