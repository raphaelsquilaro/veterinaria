package sp.senai.org.vetmark.service;

import org.springframework.stereotype.Service;
import sp.senai.org.vetmark.dto.request.VeterinarioRequest;
import sp.senai.org.vetmark.dto.response.VeterinarioResponse;
import sp.senai.org.vetmark.model.entity.Veterinario;
import sp.senai.org.vetmark.repository.VeterinarioRepository;

import java.util.List;

@Service
public class VeterinarioService {

    private final VeterinarioRepository repository;

    public VeterinarioService(VeterinarioRepository repository) {
        this.repository = repository;
    }

    // =========================
    // MÉTODOS DO THYMELEAF
    // =========================

    public List<Veterinario> findAll() {
        return repository.findAll();
    }

    public Veterinario findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException(
                                "Veterinário não encontrado"
                        )
                );
    }

    public Veterinario save(Veterinario veterinario) {
        return repository.save(veterinario);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }


    // =========================
    // MÉTODOS DA API
    // =========================

    public List<VeterinarioResponse> listarApi() {

        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }


    public VeterinarioResponse buscarApi(Long id) {

        Veterinario veterinario = findById(id);

        return toResponse(veterinario);
    }


    public VeterinarioResponse criarApi(
            VeterinarioRequest request
    ) {

        Veterinario veterinario = new Veterinario();

        veterinario.setNome(request.nome());
        veterinario.setTelefone(request.telefone());
        veterinario.setEmail(request.email());
        veterinario.setCpf(request.cpf());
        veterinario.setCrmv(request.crmv());
        veterinario.setEspecialidade(request.especialidade());
        veterinario.setAtivo(request.ativo());

        Veterinario salvo =
                repository.save(veterinario);

        return toResponse(salvo);
    }


    public VeterinarioResponse atualizarApi(
            Long id,
            VeterinarioRequest request
    ) {

        Veterinario veterinario = findById(id);

        veterinario.setNome(request.nome());
        veterinario.setTelefone(request.telefone());
        veterinario.setEmail(request.email());
        veterinario.setCpf(request.cpf());
        veterinario.setCrmv(request.crmv());
        veterinario.setEspecialidade(request.especialidade());
        veterinario.setAtivo(request.ativo());

        Veterinario atualizado =
                repository.save(veterinario);

        return toResponse(atualizado);
    }


    public void excluirApi(Long id) {

        Veterinario veterinario = findById(id);

        repository.delete(veterinario);
    }


    // =========================
    // ENTITY → RESPONSE
    // =========================

    private VeterinarioResponse toResponse(
            Veterinario veterinario
    ) {

        return new VeterinarioResponse(
                veterinario.getId(),
                veterinario.getNome(),
                veterinario.getTelefone(),
                veterinario.getEmail(),
                veterinario.getCpf(),
                veterinario.getCrmv(),
                veterinario.getEspecialidade(),
                veterinario.getAtivo()
        );
    }
}