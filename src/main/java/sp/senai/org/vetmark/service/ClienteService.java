package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.ClienteRequest;
import sp.senai.org.vetmark.dto.response.ClienteResponse;
import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.repository.ClienteRepository;

import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<ClienteResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ClienteResponse findById(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow();
        return toResponse(cliente);
    }

    public ClienteResponse create(ClienteRequest request) {
        Cliente cliente = new Cliente();

        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
        cliente.setCpf(request.cpf());
        cliente.setDataCadastro(request.dataNascimento());
        cliente.setPets(request.pets());

        Cliente savedCliente = repository.save(cliente);

        return toResponse(savedCliente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ClienteResponse toResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getDataCadastro(),
                cliente.getPets()
        );
    }
}
