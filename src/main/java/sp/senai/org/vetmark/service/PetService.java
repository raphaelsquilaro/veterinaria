package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.PetRequest;
import sp.senai.org.vetmark.dto.response.PetResponse;
import sp.senai.org.vetmark.model.entity.Pet;
import sp.senai.org.vetmark.repository.ClienteRepository;
import sp.senai.org.vetmark.repository.PetRepository;

import java.util.List;

@Service
public class PetService {

    private final PetRepository repository;
    private final ClienteRepository clienteRepository;

    public PetService(
            PetRepository repository,
            ClienteRepository clienteRepository
    ) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
    }

    // =========================
    // MÉTODOS DO THYMELEAF
    // =========================

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


    // =========================
    // MÉTODOS DA API
    // =========================

    public List<PetResponse> listarApi() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public PetResponse buscarApi(Long id) {

        Pet pet = findById(id);

        return toResponse(pet);
    }

    public PetResponse criarApi(PetRequest request) {

        Pet pet = new Pet();

        pet.setNome(request.nome());
        pet.setEspecie(request.especie());
        pet.setRaca(request.raca());
        pet.setDataNascimento(request.dataNascimento());

        pet.setCliente(
                clienteRepository.findById(request.clienteId())
                        .orElseThrow(() ->
                                new RuntimeException("Cliente não encontrado")
                        )
        );

        Pet salvo = repository.save(pet);

        return toResponse(salvo);
    }

    public PetResponse atualizarApi(
            Long id,
            PetRequest request
    ) {

        Pet pet = findById(id);

        pet.setNome(request.nome());
        pet.setEspecie(request.especie());
        pet.setRaca(request.raca());
        pet.setDataNascimento(request.dataNascimento());

        pet.setCliente(
                clienteRepository.findById(request.clienteId())
                        .orElseThrow(() ->
                                new RuntimeException("Cliente não encontrado")
                        )
        );

        Pet atualizado = repository.save(pet);

        return toResponse(atualizado);
    }

    public void excluirApi(Long id) {

        Pet pet = findById(id);

        repository.delete(pet);
    }


    // =========================
    // CONVERSÃO ENTITY → RESPONSE
    // =========================

    private PetResponse toResponse(Pet pet) {

        return new PetResponse(
                pet.getId(),
                pet.getNome(),
                pet.getEspecie(),
                pet.getRaca(),
                pet.getDataNascimento(),
                pet.getCliente() != null
                        ? pet.getCliente().getId()
                        : null,
                pet.getCliente() != null
                        ? pet.getCliente().getNome()
                        : null
        );
    }
}