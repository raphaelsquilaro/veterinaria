package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.Pedido;

public interface PedidoRepository
        extends JpaRepository<Pedido, Long> {

}
