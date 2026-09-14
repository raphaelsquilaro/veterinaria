package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.HistoricoClinico;

import java.util.List;

public interface HistoricoClinicoRepository
        extends JpaRepository<HistoricoClinico, Long> {

    List<HistoricoClinico> findByPetIdOrderByDataConsultaDesc(Long petId);
}