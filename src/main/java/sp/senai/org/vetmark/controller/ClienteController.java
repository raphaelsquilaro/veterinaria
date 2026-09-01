package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.repository.ClienteRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository repository;

    @GetMapping("/listagem")
    public String listarCliente(Model model) {

        model.addAttribute(
                "clientes",
                repository.findAll()
        );

        return "";
    }

    @GetMapping("/cadastro")
    public String cadastroCliente(Model model) {

        model.addAttribute(
                "cliente",
                new Cliente()
        );

        return "";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(
            @PathVariable Long id,
            Model model
    ) {
        Cliente cliente =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cliente não Encontrado"
                                )
                        );

        model.addAttribute(
                "cliente",
                cliente
        );

        return "";
    }

    @PostMapping("/salvar")
    public String salvarCliente(
            @Valid @ModelAttribute Cliente cliente,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "";
        }

        repository.save(cliente);

        return "redirect:";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCliente(
            @PathVariable Long id
    ) {
        repository.deleteById(id);

        return "";
    }
}
