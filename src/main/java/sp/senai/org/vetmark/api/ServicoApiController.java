package sp.senai.org.vetmark.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.dto.request.ServicoRequest;
import sp.senai.org.vetmark.dto.response.ServicoResponse;
import sp.senai.org.vetmark.service.ServicoService;

import java.util.List;

@RestController
@RequestMapping("/api/servicos")
public class ServicoApiController {

    private final ServicoService service;

    public ServicoApiController(ServicoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ServicoResponse> listar() {
        return service.listarApi();
    }

    @GetMapping("/{id}")
    public ServicoResponse buscar(
            @PathVariable Long id
    ) {
        return service.buscarApi(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ServicoResponse criar(
            @RequestBody ServicoRequest request
    ) {
        return service.criarApi(request);
    }

    @PutMapping("/{id}")
    public ServicoResponse atualizar(
            @PathVariable Long id,
            @RequestBody ServicoRequest request
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