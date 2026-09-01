package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.Cliente;

public interface ClienteRepository
        extends JpaRepository<Cliente, Long> {

}
