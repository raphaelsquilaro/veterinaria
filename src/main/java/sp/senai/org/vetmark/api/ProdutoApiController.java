package sp.senai.org.vetmark.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.dto.request.ProdutoRequest;
import sp.senai.org.vetmark.dto.response.ProdutoResponse;
import sp.senai.org.vetmark.service.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoApiController {

    private final ProdutoService service;

    public ProdutoApiController(ProdutoService service) {
        this.service = service;
    }

    @GetMapping
    public List<ProdutoResponse> listar() {
        return service.listarApi();
    }

    @GetMapping("/{id}")
    public ProdutoResponse buscar(
            @PathVariable Long id
    ) {
        return service.buscarApi(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProdutoResponse criar(
            @RequestBody ProdutoRequest request
    ) {
        return service.criarApi(request);
    }

    @PutMapping("/{id}")
    public ProdutoResponse atualizar(
            @PathVariable Long id,
            @RequestBody ProdutoRequest request
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
