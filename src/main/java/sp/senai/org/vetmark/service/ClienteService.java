package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.ClienteRequest;
import sp.senai.org.vetmark.dto.response.ClienteResponse;
import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.repository.ClienteRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClienteService {

    private final ClienteRepository repository;

    public ClienteService(ClienteRepository repository) {
        this.repository = repository;
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Cliente não encontrado")
                );
    }

    public Cliente save(Cliente cliente) {

        if (cliente.getId() == null) {
            cliente.setDataCadastro(LocalDateTime.now());
        }

        return repository.save(cliente);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    // =====================================================
    // API
    // =====================================================

    public List<ClienteResponse> listarApi() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public ClienteResponse buscarApi(Long id) {

        Cliente cliente = findById(id);

        return toResponse(cliente);
    }


    public ClienteResponse criarApi(
            ClienteRequest request
    ) {

        Cliente cliente = new Cliente();

        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
        cliente.setCpf(request.cpf());

        cliente.setDataCadastro(LocalDateTime.now());

        Cliente salvo = repository.save(cliente);

        return toResponse(salvo);
    }


    public ClienteResponse atualizarApi(
            Long id,
            ClienteRequest request
    ) {

        Cliente cliente = findById(id);

        cliente.setNome(request.nome());
        cliente.setTelefone(request.telefone());
        cliente.setEmail(request.email());
        cliente.setCpf(request.cpf());

        Cliente atualizado = repository.save(cliente);

        return toResponse(atualizado);
    }


    public void excluirApi(Long id) {

        Cliente cliente = findById(id);

        repository.delete(cliente);
    }


    private ClienteResponse toResponse(
            Cliente cliente
    ) {

        return new ClienteResponse(
                cliente.getId(),
                cliente.getNome(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getDataCadastro()
        );
    }
}