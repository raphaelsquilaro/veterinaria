package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}