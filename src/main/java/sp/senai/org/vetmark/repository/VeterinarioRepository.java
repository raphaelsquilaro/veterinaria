package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.Veterinario;

public interface VeterinarioRepository
        extends JpaRepository<Veterinario, Long> {

}
