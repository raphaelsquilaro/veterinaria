package sp.senai.org.vetmark.api;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.dto.request.VeterinarioRequest;
import sp.senai.org.vetmark.dto.response.VeterinarioResponse;
import sp.senai.org.vetmark.service.VeterinarioService;

import java.util.List;

@RestController
@RequestMapping("/api/veterinarios")
public class VeterinarioApiController {

    private final VeterinarioService service;

    public VeterinarioApiController(
            VeterinarioService service
    ) {
        this.service = service;
    }

    @GetMapping
    public List<VeterinarioResponse> listar() {
        return service.listarApi();
    }

    @GetMapping("/{id}")
    public VeterinarioResponse buscar(
            @PathVariable Long id
    ) {
        return service.buscarApi(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public VeterinarioResponse criar(
            @RequestBody VeterinarioRequest request
    ) {
        return service.criarApi(request);
    }

    @PutMapping("/{id}")
    public VeterinarioResponse atualizar(
            @PathVariable Long id,
            @RequestBody VeterinarioRequest request
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