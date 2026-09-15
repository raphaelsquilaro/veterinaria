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

    // =========================
    // MÉTODOS DO THYMELEAF
    // =========================

    public List<Servico> findAll() {
        return repository.findAll();
    }

    public Servico findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Serviço não encontrado")
                );
    }

    public Servico save(Servico servico) {
        return repository.save(servico);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // =========================
    // MÉTODOS DA API
    // =========================

    public List<ServicoResponse> listarApi() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public ServicoResponse buscarApi(Long id) {

        return toResponse(findById(id));
    }

    public ServicoResponse criarApi(ServicoRequest request) {

        Servico servico = new Servico();

        servico.setNome(request.nome());
        servico.setDescricao(request.descricao());
        servico.setValor(request.valor());
        servico.setAtivo(request.ativo());

        return toResponse(repository.save(servico));
    }

    public ServicoResponse atualizarApi(
            Long id,
            ServicoRequest request
    ) {

        Servico servico = findById(id);

        servico.setNome(request.nome());
        servico.setDescricao(request.descricao());
        servico.setValor(request.valor());
        servico.setAtivo(request.ativo());

        return toResponse(repository.save(servico));
    }

    public void excluirApi(Long id) {

        repository.delete(findById(id));
    }

    // =========================
    // CONVERSÃO ENTITY → RESPONSE
    // =========================

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