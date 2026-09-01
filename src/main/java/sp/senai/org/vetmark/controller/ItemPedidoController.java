package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.ItemPedido;
import sp.senai.org.vetmark.repository.ItemPedidoRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item-pedido")
public class ItemPedidoController {

    private final ItemPedidoRepository repository;

    @GetMapping("/listagem")
    public String lisatrItem(Model model) {

        model.addAttribute(
                "itens-pedidos",
                repository.findAll()
        );

        return "";
    }

    @GetMapping("/cadastro")
    public String cadastroitem(Model model) {

        model.addAttribute(
                "item-pedido",
                new ItemPedido()
        );

        return "";
    }

    @GetMapping("/editar/{id}")
    public String editarItem(
            @PathVariable Long id,
            Model model
    ) {
        ItemPedido itemPedido =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Item não Encontrado"
                                )
                        );

        model.addAttribute(
                "item-pedido",
                itemPedido
        );

        return "";
    }

    @PostMapping("/salvar")
    public String salvarItem(
            @Valid @ModelAttribute ItemPedido itemPedido,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "";
        }

        repository.save(itemPedido);

        return "redirect:";
    }

    @GetMapping("/excluir/{id}")
    public String excluirItem(
            @PathVariable Long id
    ) {
        repository.deleteById(id);

        return "";
    }
}
