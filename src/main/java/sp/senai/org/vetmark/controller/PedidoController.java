package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.Pedido;
import sp.senai.org.vetmark.repository.PedidoRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoRepository repository;

    @GetMapping("/listagem")
    public String listagemPedido(Model model) {
        model.addAttribute(
                "pedidos",
                repository.findAll()
        );

        return "";
    }

    @GetMapping("/cadastro")
    public String cadastroPedido(Model model) {

        model.addAttribute(
                "pedido",
                new Pedido()
        );

        return "";
    }

    @GetMapping("/editar/{id}")
    public String editarPedido(
            @PathVariable Long id,
            Model model
    ) {
        Pedido pedido =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pedido não Encontrado"
                                )
                        );

        model.addAttribute(
                "pedido",
                pedido
        );

        return "";
    }

    @PostMapping("/salvar")
    public String salvarPedido(
            @Valid @ModelAttribute Pedido pedido,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "";
        }

        repository.save(pedido);

        return "redirect:";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPedido(
            @PathVariable Long id
    ) {
        repository.deleteById(id);

        return "";
    }
}
