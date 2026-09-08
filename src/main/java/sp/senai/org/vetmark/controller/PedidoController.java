package sp.senai.org.vetmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.ItemPedido;
import sp.senai.org.vetmark.model.entity.Pedido;
import sp.senai.org.vetmark.model.entity.Produto;
import sp.senai.org.vetmark.model.enums.StatusPedido;
import sp.senai.org.vetmark.repository.ClienteRepository;
import sp.senai.org.vetmark.repository.PedidoRepository;
import sp.senai.org.vetmark.repository.ProdutoRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pedido")
public class PedidoController {

    private final PedidoRepository repository;
    private final ClienteRepository clienteRepository;
    private final ProdutoRepository produtoRepository;


    @GetMapping("/listagem")
    public String listagemPedido(Model model) {

        model.addAttribute(
                "pedidos",
                repository.findAll()
        );

        return "pedido/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastroPedido(Model model) {

        Pedido pedido = new Pedido();

        pedido.setDataPedido(LocalDateTime.now());
        pedido.setStatus(StatusPedido.ABERTO);
        pedido.setValorTotal(BigDecimal.ZERO);

        model.addAttribute("pedido", pedido);

        var produtos = produtoRepository.findAll();

        System.out.println("=================================");
        System.out.println("PRODUTOS ENCONTRADOS: " + produtos.size());
        System.out.println("PRODUTOS: " + produtos);
        System.out.println("=================================");

        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("produtos", produtos);

        return "pedido/cadastro";
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
                                        "Pedido não encontrado"
                                )
                        );

        model.addAttribute(
                "pedido",
                pedido
        );

        carregarDadosFormulario(model);

        return "pedido/cadastro";
    }


    @PostMapping("/salvar")
    public String salvarPedido(
            @ModelAttribute("pedido") Pedido pedido,
            @RequestParam Long clienteId,
            @RequestParam(required = false) List<Long> produtoIds,
            @RequestParam(required = false) List<Integer> quantidades
    ) {

        pedido.setCliente(
                clienteRepository.findById(clienteId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cliente não encontrado"
                                )
                        )
        );


        pedido.getItens().clear();


        BigDecimal valorTotalPedido =
                BigDecimal.ZERO;


        if (
                produtoIds != null &&
                        quantidades != null
        ) {

            for (
                    int i = 0;
                    i < produtoIds.size();
                    i++
            ) {

                Long produtoId =
                        produtoIds.get(i);

                Integer quantidade =
                        quantidades.get(i);


                if (
                        produtoId == null ||
                                quantidade == null ||
                                quantidade <= 0
                ) {
                    continue;
                }


                Produto produto =
                        produtoRepository.findById(produtoId)
                                .orElseThrow(() ->
                                        new ResourceNotFoundException(
                                                "Produto não encontrado"
                                        )
                                );


                if (!produto.getAtivo()) {

                    throw new IllegalArgumentException(
                            "O produto " +
                                    produto.getNome() +
                                    " está inativo."
                    );
                }


                if (
                        quantidade >
                                produto.getEstoque()
                ) {

                    throw new IllegalArgumentException(
                            "Estoque insuficiente para o produto: " +
                                    produto.getNome()
                    );
                }


                BigDecimal valorUnitario =
                        produto.getPreco();


                BigDecimal valorTotalItem =
                        valorUnitario.multiply(
                                BigDecimal.valueOf(
                                        quantidade
                                )
                        );


                ItemPedido item =
                        new ItemPedido();


                item.setQuantidade(
                        quantidade
                );

                item.setValorUnitario(
                        valorUnitario
                );

                item.setValorTotal(
                        valorTotalItem
                );

                item.setProduto(
                        produto
                );

                item.setPedido(
                        pedido
                );


                pedido.getItens().add(
                        item
                );


                valorTotalPedido =
                        valorTotalPedido.add(
                                valorTotalItem
                        );
            }
        }


        pedido.setValorTotal(
                valorTotalPedido
        );


        repository.save(pedido);


        return "redirect:/pedido/listagem";
    }


    @GetMapping("/excluir/{id}")
    public String excluirPedido(
            @PathVariable Long id
    ) {

        repository.deleteById(id);

        return "redirect:/pedido/listagem";
    }


    private void carregarDadosFormulario(
            Model model
    ) {

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        model.addAttribute(
                "produtos",
                produtoRepository.findAll()
        );
    }
}