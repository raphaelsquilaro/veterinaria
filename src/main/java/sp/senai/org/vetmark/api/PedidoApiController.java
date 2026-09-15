package sp.senai.org.vetmark.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.dto.request.PedidoRequest;
import sp.senai.org.vetmark.dto.response.PedidoResponse;
import sp.senai.org.vetmark.service.PedidoService;

import java.util.List;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoApiController {

    private final PedidoService service;

    public PedidoApiController(PedidoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PedidoResponse> listar() {
        return service.listarApi();
    }

    @GetMapping("/{id}")
    public PedidoResponse buscar(
            @PathVariable Long id
    ) {
        return service.buscarApi(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PedidoResponse criar(
            @RequestBody PedidoRequest request
    ) {
        return service.criarApi(request);
    }

    @PutMapping("/{id}")
    public PedidoResponse atualizar(
            @PathVariable Long id,
            @RequestBody PedidoRequest request
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
