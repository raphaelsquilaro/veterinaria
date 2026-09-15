package sp.senai.org.vetmark.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.dto.request.PetRequest;
import sp.senai.org.vetmark.dto.response.PetResponse;
import sp.senai.org.vetmark.service.PetService;

import java.util.List;

@RestController
@RequestMapping("/api/pets")
@RequiredArgsConstructor
public class PetApiController {

    private final PetService service;

    @GetMapping
    public List<PetResponse> listar() {
        return service.listarApi();
    }

    @GetMapping("/{id}")
    public PetResponse buscar(@PathVariable Long id) {
        return service.buscarApi(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PetResponse criar(
            @RequestBody PetRequest request
            ) {
        return service.criarApi(request);
    }

    @PutMapping("/{id}")
    public PetResponse atualizar(
            @PathVariable Long id,
            @RequestBody PetRequest request
    ) {
        return service.atualizarApi(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void excluir(@PathVariable Long id) {
        service.excluirApi(id);
    }
}
