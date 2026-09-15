package sp.senai.org.vetmark.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.dto.request.HistoricoClinicoRequest;
import sp.senai.org.vetmark.dto.response.HistoricoClinicoResponse;
import sp.senai.org.vetmark.service.HistoricoClinicoService;

import java.util.List;

@RestController
@RequestMapping("/api/historicos-clinicos")
public class HistoricoClinicoApiController {

    private final HistoricoClinicoService service;

    public HistoricoClinicoApiController(
            HistoricoClinicoService service
    ) {
        this.service = service;
    }

    @GetMapping
    public List<HistoricoClinicoResponse> listar() {
        return service.listarApi();
    }

    @GetMapping("/{id}")
    public HistoricoClinicoResponse buscar(
            @PathVariable Long id
    ) {
        return service.buscarApi(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HistoricoClinicoResponse criar(
            @RequestBody HistoricoClinicoRequest request
    ) {
        return service.criarApi(request);
    }

    @PutMapping("/{id}")
    public HistoricoClinicoResponse atualizar(
            @PathVariable Long id,
            @RequestBody HistoricoClinicoRequest request
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