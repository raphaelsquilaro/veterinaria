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
import sp.senai.org.vetmark.repository.PedidoRepository;
import sp.senai.org.vetmark.repository.ProdutoRepository;

import java.math.BigDecimal;

@Controller
@RequiredArgsConstructor
@RequestMapping("/item-pedido")
public class ItemPedidoController {

    private final ItemPedidoRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;


    @GetMapping("/listagem")
    public String listagemItemPedido(Model model) {

        model.addAttribute(
                "itens",
                repository.findAll()
        );

        return "item-pedido/listagem";
    }


    @GetMapping("/cadastro")
    public String cadastroItemPedido(Model model) {

        model.addAttribute(
                "itemPedido",
                new ItemPedido()
        );

        carregarDadosFormulario(model);

        return "item-pedido/cadastro";
    }


    @GetMapping("/editar/{id}")
    public String editarItemPedido(
            @PathVariable Long id,
            Model model
    ) {

        ItemPedido itemPedido =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Item do pedido não encontrado"
                                )
                        );

        model.addAttribute(
                "itemPedido",
                itemPedido
        );

        carregarDadosFormulario(model);

        return "item-pedido/cadastro";
    }


    @PostMapping("/salvar")
    public String salvarItemPedido(
            @Valid @ModelAttribute("itemPedido") ItemPedido itemPedido,
            BindingResult result,
            @RequestParam Long pedidoId,
            @RequestParam Long produtoId,
            Model model
    ) {

        if (result.hasErrors()) {

            carregarDadosFormulario(model);

            return "item-pedido/cadastro";
        }


        // Buscar o pedido
        itemPedido.setPedido(
                pedidoRepository.findById(pedidoId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pedido não encontrado"
                                )
                        )
        );


        // Buscar o produto
        var produto =
                produtoRepository.findById(produtoId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Produto não encontrado"
                                )
                        );


        // Verificar se o produto está ativo
        if (!produto.getAtivo()) {

            throw new IllegalArgumentException(
                    "O produto selecionado está inativo."
            );
        }


        // Usar o preço cadastrado no produto
        BigDecimal valorUnitario =
                produto.getPreco();


        // Calcular o valor total do item
        BigDecimal valorTotal =
                valorUnitario.multiply(
                        BigDecimal.valueOf(
                                itemPedido.getQuantidade()
                        )
                );


        itemPedido.setProduto(produto);

        itemPedido.setValorUnitario(
                valorUnitario
        );

        itemPedido.setValorTotal(
                valorTotal
        );


        repository.save(itemPedido);

        return "redirect:/item-pedido/listagem";
    }


    @GetMapping("/excluir/{id}")
    public String excluirItemPedido(
            @PathVariable Long id
    ) {

        repository.deleteById(id);

        return "redirect:/item-pedido/listagem";
    }


    private void carregarDadosFormulario(Model model) {

        model.addAttribute(
                "pedidos",
                pedidoRepository.findAll()
        );

        model.addAttribute(
                "produtos",
                produtoRepository.findAll()
        );
    }
}