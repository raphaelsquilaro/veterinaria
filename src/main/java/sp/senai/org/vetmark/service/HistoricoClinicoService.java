package sp.senai.org.vetmark.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.HistoricoClinico;
import sp.senai.org.vetmark.repository.HistoricoClinicoRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HistoricoClinicoService {

    private final HistoricoClinicoRepository repository;

    public List<HistoricoClinico> findAll() {
        return repository.findAll();
    }

    public HistoricoClinico findById(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Histórico clínico não encontrado"
                        )
                );
    }

    public List<HistoricoClinico> findByPetId(Long petId) {

        return repository.findByPetIdOrderByDataConsultaDesc(petId);
    }

    public HistoricoClinico save(
            HistoricoClinico historicoClinico
    ) {
        return repository.save(historicoClinico);
    }

    public void delete(Long id) {

        HistoricoClinico historicoClinico = findById(id);

        repository.delete(historicoClinico);
    }
}