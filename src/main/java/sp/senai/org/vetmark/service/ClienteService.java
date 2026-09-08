package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
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
}