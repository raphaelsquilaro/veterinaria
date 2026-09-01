package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.MovimentacaoFinanceira;

public interface MovimentacaoFinanceiraRepository
        extends JpaRepository<MovimentacaoFinanceira, Long> {

}
