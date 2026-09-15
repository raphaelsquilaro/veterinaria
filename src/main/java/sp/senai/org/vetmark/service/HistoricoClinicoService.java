package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.HistoricoClinicoRequest;
import sp.senai.org.vetmark.dto.response.HistoricoClinicoResponse;
import sp.senai.org.vetmark.model.entity.Agendamento;
import sp.senai.org.vetmark.model.entity.HistoricoClinico;
import sp.senai.org.vetmark.model.entity.Pet;
import sp.senai.org.vetmark.model.entity.Veterinario;
import sp.senai.org.vetmark.repository.AgendamentoRepository;
import sp.senai.org.vetmark.repository.HistoricoClinicoRepository;
import sp.senai.org.vetmark.repository.PetRepository;
import sp.senai.org.vetmark.repository.VeterinarioRepository;

import java.util.List;

@Service
public class HistoricoClinicoService {

    private final HistoricoClinicoRepository repository;
    private final PetRepository petRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final AgendamentoRepository agendamentoRepository;

    public HistoricoClinicoService(
            HistoricoClinicoRepository repository,
            PetRepository petRepository,
            VeterinarioRepository veterinarioRepository,
            AgendamentoRepository agendamentoRepository
    ) {
        this.repository = repository;
        this.petRepository = petRepository;
        this.veterinarioRepository = veterinarioRepository;
        this.agendamentoRepository = agendamentoRepository;
    }

    // =========================
    // MÉTODOS DO THYMELEAF
    // =========================

    public List<HistoricoClinico> findAll() {
        return repository.findAll();
    }

    public HistoricoClinico findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Histórico clínico não encontrado")
                );
    }

    public List<HistoricoClinico> findByPetId(Long petId) {
        return repository.findByPetId(petId);
    }

    public HistoricoClinico save(HistoricoClinico historicoClinico) {
        return repository.save(historicoClinico);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // =========================
    // MÉTODOS DA API
    // =========================

    public List<HistoricoClinicoResponse> listarApi() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public HistoricoClinicoResponse buscarApi(Long id) {

        return toResponse(findById(id));
    }

    public HistoricoClinicoResponse criarApi(
            HistoricoClinicoRequest request
    ) {

        HistoricoClinico historico = new HistoricoClinico();

        historico.setDataConsulta(request.dataConsulta());
        historico.setQueixaPrincipal(request.queixaPrincipal());
        historico.setDiagnostico(request.diagnostico());
        historico.setTratamento(request.tratamento());
        historico.setMedicacao(request.medicacao());
        historico.setObservacoes(request.observacoes());

        historico.setPet(
                petRepository.findById(request.petId())
                        .orElseThrow(() ->
                                new RuntimeException("Pet não encontrado")
                        )
        );

        historico.setVeterinario(
                veterinarioRepository.findById(request.veterinarioId())
                        .orElseThrow(() ->
                                new RuntimeException("Veterinário não encontrado")
                        )
        );

        if (request.agendamentoId() != null) {

            historico.setAgendamento(
                    agendamentoRepository.findById(request.agendamentoId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Agendamento não encontrado"
                                    )
                            )
            );
        }

        return toResponse(repository.save(historico));
    }

    public HistoricoClinicoResponse atualizarApi(
            Long id,
            HistoricoClinicoRequest request
    ) {

        HistoricoClinico historico = findById(id);

        historico.setDataConsulta(request.dataConsulta());
        historico.setQueixaPrincipal(request.queixaPrincipal());
        historico.setDiagnostico(request.diagnostico());
        historico.setTratamento(request.tratamento());
        historico.setMedicacao(request.medicacao());
        historico.setObservacoes(request.observacoes());

        historico.setPet(
                petRepository.findById(request.petId())
                        .orElseThrow(() ->
                                new RuntimeException("Pet não encontrado")
                        )
        );

        historico.setVeterinario(
                veterinarioRepository.findById(request.veterinarioId())
                        .orElseThrow(() ->
                                new RuntimeException("Veterinário não encontrado")
                        )
        );

        if (request.agendamentoId() != null) {

            historico.setAgendamento(
                    agendamentoRepository.findById(request.agendamentoId())
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Agendamento não encontrado"
                                    )
                            )
            );

        } else {

            historico.setAgendamento(null);
        }

        return toResponse(repository.save(historico));
    }

    public void excluirApi(Long id) {

        repository.delete(findById(id));
    }

    // =========================
    // ENTITY → RESPONSE
    // =========================

    private HistoricoClinicoResponse toResponse(
            HistoricoClinico historico
    ) {

        Pet pet = historico.getPet();
        Veterinario veterinario = historico.getVeterinario();
        Agendamento agendamento = historico.getAgendamento();

        return new HistoricoClinicoResponse(
                historico.getId(),
                historico.getDataConsulta(),
                historico.getQueixaPrincipal(),
                historico.getDiagnostico(),
                historico.getTratamento(),
                historico.getMedicacao(),
                historico.getObservacoes(),

                pet != null ? pet.getId() : null,
                pet != null ? pet.getNome() : null,

                veterinario != null ? veterinario.getId() : null,
                veterinario != null ? veterinario.getNome() : null,

                agendamento != null ? agendamento.getId() : null
        );
    }
}