package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.PetRequest;
import sp.senai.org.vetmark.dto.response.PetResponse;
import sp.senai.org.vetmark.model.entity.Pet;
import sp.senai.org.vetmark.repository.PetRepository;

import java.util.List;

@Service
public class PetService {

    private final PetRepository repository;

    public PetService(PetRepository repository) {
        this.repository = repository;
    }

    public List<PetResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PetResponse findById(Long id) {
        Pet pet = repository.findById(id)
                .orElseThrow();
        return toResponse(pet);
    }

    public PetResponse create(PetRequest request) {
        Pet pet = new Pet();

        pet.setNome(request.nome());
        pet.setEspecie(request.especie());
        pet.setRaca(request.raca());
        pet.setDataNascimento(request.dataNascimento());
        pet.setCliente(request.cliente());

        Pet savedPet = repository.save(pet);

        return toResponse(savedPet);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PetResponse toResponse(Pet pet) {
        return new PetResponse(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getDataNascimento(),
                pet.getCliente()
        );
    }
}
