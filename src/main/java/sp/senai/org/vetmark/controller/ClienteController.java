package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.service.ClienteService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteService service;

    @GetMapping("/listagem")
    public String listarCliente(Model model) {

        model.addAttribute(
                "clientes",
                service.findAll()
        );

        return "cliente/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastroCliente(Model model) {

        model.addAttribute(
                "cliente",
                new Cliente()
        );

        return "cliente/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String editarCliente(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
                "cliente",
                service.findById(id)
        );

        return "cliente/cadastro";
    }

    @PostMapping("/salvar")
    public String salvarCliente(
            @Valid @ModelAttribute("cliente") Cliente cliente,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "cliente/cadastro";
        }

        service.save(cliente);

        return "redirect:/cliente/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluirCliente(
            @PathVariable Long id
    ) {

        service.delete(id);

        return "redirect:/cliente/listagem";
    }
}