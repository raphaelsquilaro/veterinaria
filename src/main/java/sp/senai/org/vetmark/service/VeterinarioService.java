package sp.senai.org.vetmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.Veterinario;
import sp.senai.org.vetmark.repository.VeterinarioRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class VeterinarioService {

    private final VeterinarioRepository repository;

    public List<Veterinario> findAll() {
        return repository.findAll();
    }

    public Veterinario findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Veterinário não encontrado"
                        )
                );
    }

    public Veterinario save(Veterinario veterinario) {
        return repository.save(veterinario);
    }

    public void delete(Long id) {

        Veterinario veterinario = findById(id);

        repository.delete(veterinario);
    }
}