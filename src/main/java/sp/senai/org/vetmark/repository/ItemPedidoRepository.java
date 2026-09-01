package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.ItemPedido;

public interface ItemPedidoRepository
        extends JpaRepository<ItemPedido, Long> {

}
