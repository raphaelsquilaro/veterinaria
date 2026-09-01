package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.ServicoRequest;
import sp.senai.org.vetmark.dto.response.ServicoResponse;
import sp.senai.org.vetmark.model.entity.Servico;
import sp.senai.org.vetmark.repository.ServicoRepository;

import java.util.List;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public List<ServicoResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ServicoResponse findById(Long id) {
        Servico servico = repository.findById(id)
                .orElseThrow();
        return toResponse(servico);
    }

    public ServicoResponse create(ServicoRequest request) {
        Servico servico = new Servico();

        servico.setNome(request.nome());
        servico.setDescricao(request.descricao());
        servico.setValor(request.valor());
        servico.setAtivo(request.ativo());

        Servico savedServico = repository.save(servico);

        return toResponse(savedServico);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private ServicoResponse toResponse(Servico servico) {
        return new ServicoResponse(
                servico.getId(),
                servico.getNome(),
                servico.getDescricao(),
                servico.getValor(),
                servico.getAtivo()
        );
    }
}
