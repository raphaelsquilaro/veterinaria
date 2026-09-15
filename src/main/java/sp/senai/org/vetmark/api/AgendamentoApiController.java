package sp.senai.org.vetmark.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.dto.request.AgendamentoRequest;
import sp.senai.org.vetmark.dto.response.AgendamentoResponse;
import sp.senai.org.vetmark.service.AgendamentoService;

import java.util.List;

@RestController
@RequestMapping("/api/agendamentos")
public class AgendamentoApiController {

    private final AgendamentoService service;

    public AgendamentoApiController(AgendamentoService service) {
        this.service = service;
    }

    @GetMapping
    public List<AgendamentoResponse> listar() {
        return service.listarApi();
    }

    @GetMapping("/{id}")
    public AgendamentoResponse buscar(
            @PathVariable Long id
    ) {
        return service.buscarApi(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AgendamentoResponse criar(
            @RequestBody AgendamentoRequest request
    ) {
        return service.criarApi(request);
    }

    @PutMapping("/{id}")
    public AgendamentoResponse atualizar(
            @PathVariable Long id,
            @RequestBody AgendamentoRequest request
    ) {
        return service.atualizarApi(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(
            @PathVariable Long id
    ) {
        service.excluirApi(id);
    }
}
