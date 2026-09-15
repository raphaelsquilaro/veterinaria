package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.AgendamentoRequest;
import sp.senai.org.vetmark.dto.response.AgendamentoResponse;
import sp.senai.org.vetmark.model.entity.Agendamento;
import sp.senai.org.vetmark.repository.AgendamentoRepository;
import sp.senai.org.vetmark.repository.ClienteRepository;
import sp.senai.org.vetmark.repository.PetRepository;
import sp.senai.org.vetmark.repository.ServicoRepository;
import sp.senai.org.vetmark.repository.VeterinarioRepository;

import java.util.List;

@Service
public class AgendamentoService {

    private final AgendamentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;
    private final VeterinarioRepository veterinarioRepository;

    public AgendamentoService(
            AgendamentoRepository repository,
            ClienteRepository clienteRepository,
            PetRepository petRepository,
            ServicoRepository servicoRepository,
            VeterinarioRepository veterinarioRepository
    ) {
        this.repository = repository;
        this.clienteRepository = clienteRepository;
        this.petRepository = petRepository;
        this.servicoRepository = servicoRepository;
        this.veterinarioRepository = veterinarioRepository;
    }

    // =========================
    // MÉTODOS DO THYMELEAF
    // =========================

    public List<Agendamento> findAll() {
        return repository.findAll();
    }

    public Agendamento findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Agendamento não encontrado")
                );
    }

    public Agendamento save(Agendamento agendamento) {
        return repository.save(agendamento);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    // =========================
    // MÉTODOS DA API
    // =========================

    public List<AgendamentoResponse> listarApi() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public AgendamentoResponse buscarApi(Long id) {

        Agendamento agendamento = findById(id);

        return toResponse(agendamento);
    }

    public AgendamentoResponse criarApi(
            AgendamentoRequest request
    ) {

        Agendamento agendamento = new Agendamento();

        agendamento.setDataHora(request.getDataHora());
        agendamento.setStatus(request.getStatus());
        agendamento.setObservacoes(request.getObservacoes());

        agendamento.setCliente(
                clienteRepository.findById(request.getClienteId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cliente não encontrado"
                                )
                        )
        );

        agendamento.setPet(
                petRepository.findById(request.getPetId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pet não encontrado"
                                )
                        )
        );

        agendamento.setVeterinario(
                veterinarioRepository.findById(
                                request.getVeterinarioId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Veterinário não encontrado"
                                )
                        )
        );

        agendamento.setServico(
                servicoRepository.findById(request.getServicoId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Serviço não encontrado"
                                )
                        )
        );

        Agendamento salvo = repository.save(agendamento);

        return toResponse(salvo);
    }

    public AgendamentoResponse atualizarApi(
            Long id,
            AgendamentoRequest request
    ) {

        Agendamento agendamento = findById(id);

        agendamento.setDataHora(request.getDataHora());
        agendamento.setStatus(request.getStatus());
        agendamento.setObservacoes(request.getObservacoes());

        agendamento.setCliente(
                clienteRepository.findById(request.getClienteId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Cliente não encontrado"
                                )
                        )
        );

        agendamento.setPet(
                petRepository.findById(request.getPetId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Pet não encontrado"
                                )
                        )
        );

        agendamento.setVeterinario(
                veterinarioRepository.findById(
                                request.getVeterinarioId()
                        )
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Veterinário não encontrado"
                                )
                        )
        );

        agendamento.setServico(
                servicoRepository.findById(request.getServicoId())
                        .orElseThrow(() ->
                                new RuntimeException(
                                        "Serviço não encontrado"
                                )
                        )
        );

        Agendamento atualizado =
                repository.save(agendamento);

        return toResponse(atualizado);
    }

    public void excluirApi(Long id) {

        Agendamento agendamento = findById(id);

        repository.delete(agendamento);
    }


    // =========================
    // ENTITY → RESPONSE
    // =========================

    private AgendamentoResponse toResponse(
            Agendamento agendamento
    ) {

        return AgendamentoResponse.builder()
                .id(agendamento.getId())
                .dataHora(agendamento.getDataHora())
                .status(agendamento.getStatus())
                .observacoes(agendamento.getObservacoes())

                .clienteId(
                        agendamento.getCliente() != null
                                ? agendamento.getCliente().getId()
                                : null
                )

                .clienteNome(
                        agendamento.getCliente() != null
                                ? agendamento.getCliente().getNome()
                                : null
                )

                .petId(
                        agendamento.getPet() != null
                                ? agendamento.getPet().getId()
                                : null
                )

                .petNome(
                        agendamento.getPet() != null
                                ? agendamento.getPet().getNome()
                                : null
                )

                .veterinarioId(
                        agendamento.getVeterinario() != null
                                ? agendamento.getVeterinario().getId()
                                : null
                )

                .veterinarioNome(
                        agendamento.getVeterinario() != null
                                ? agendamento.getVeterinario().getNome()
                                : null
                )

                .servicoId(
                        agendamento.getServico() != null
                                ? agendamento.getServico().getId()
                                : null
                )

                .servicoNome(
                        agendamento.getServico() != null
                                ? agendamento.getServico().getNome()
                                : null
                )

                .build();
    }
}