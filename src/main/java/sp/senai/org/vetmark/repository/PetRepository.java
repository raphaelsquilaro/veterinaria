package sp.senai.org.vetmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sp.senai.org.vetmark.model.entity.Pet;

public interface PetRepository
        extends JpaRepository<Pet, Long> {

}
