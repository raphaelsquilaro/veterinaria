package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.Servico;

public interface ServicoRepository
        extends JpaRepository<Servico, Long> {

}
