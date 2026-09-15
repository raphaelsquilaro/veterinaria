package sp.senai.org.vetmark.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.dto.request.ClienteRequest;
import sp.senai.org.vetmark.dto.response.ClienteResponse;
import sp.senai.org.vetmark.service.ClienteService;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@RequiredArgsConstructor
public class ClienteApiController {

    private final ClienteService service;

    @GetMapping
    public List<ClienteResponse> listar() {
        return service.listarApi();
    }

    @GetMapping("/{id}")
    public ClienteResponse buscar(
            @PathVariable Long id
    ) {
        return service.buscarApi(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteResponse criar(
            @RequestBody ClienteRequest request
    ) {
        return service.criarApi(request);
    }

    @PutMapping("/{id}")
    public ClienteResponse atualizar(
            @PathVariable Long id,
            @RequestBody ClienteRequest request
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
