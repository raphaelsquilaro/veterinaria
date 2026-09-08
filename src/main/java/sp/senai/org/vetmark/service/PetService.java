package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.model.entity.Pet;
import sp.senai.org.vetmark.repository.PetRepository;

import java.util.List;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<Pet> findAll() {
        return repository.findAll();
    }

    public Pet findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Pet não encontrado")
                );
    }

    public Pet save(Pet pet) {
        return repository.save(pet);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}