package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.AgendamentoRequest;
import sp.senai.org.vetmark.dto.response.AgendamentoResponse;
import sp.senai.org.vetmark.model.entity.Agendamento;
import sp.senai.org.vetmark.repository.AgendamentoRepository;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;

    public AgendamentoService(AgendamentoRepository repository) {
        this.repository = repository;
    }

    public List<AgendamentoResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AgendamentoResponse findById(Long id) {
        Agendamento agendamento = repository.findById(id)
                .orElseThrow();
        return toResponse(agendamento);
    }

    public AgendamentoResponse create(AgendamentoRequest request) {
        Agendamento agendamento = new Agendamento();

        agendamento.setDataHora(request.dataHora());
        agendamento.setStatus(request.status());
        agendamento.setObservacoes(request.observacoes());
        agendamento.setCliente(request.cliente());
        agendamento.setServico(request.servico());

        Agendamento savedAgendamento = repository.save(agendamento);

        return toResponse(savedAgendamento);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private AgendamentoResponse toResponse(Agendamento agendamento) {
        return new AgendamentoResponse(
                agendamento.getId(),
                agendamento.getDataHora(),
                agendamento.getStatus(),
                agendamento.getObservacoes(),
                agendamento.getCliente(),
                agendamento.getServico()
        );
    }
}
