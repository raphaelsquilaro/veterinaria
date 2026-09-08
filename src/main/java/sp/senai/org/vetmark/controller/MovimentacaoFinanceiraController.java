package sp.senai.org.vetmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.model.entity.MovimentacaoFinanceira;
import sp.senai.org.vetmark.model.enums.CategoriaDespensa;
import sp.senai.org.vetmark.model.enums.TipoMovimentacao;
import sp.senai.org.vetmark.repository.PedidoRepository;
import sp.senai.org.vetmark.service.MovimentacaoFinanceiraService;

import java.time.LocalDate;

@Controller
@RequiredArgsConstructor
@RequestMapping("/financeiro")
public class MovimentacaoFinanceiraController {

    private final MovimentacaoFinanceiraService service;
    private final PedidoRepository pedidoRepository;

    @GetMapping("/listagem")
    public String listarMovimentacoes(Model model) {

        model.addAttribute(
                "movimentacoes",
                service.findAll()
        );

        return "financeiro/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastro(Model model) {

        MovimentacaoFinanceira movimentacao =
                new MovimentacaoFinanceira();

        movimentacao.setData(LocalDate.now());

        model.addAttribute(
                "movimentacao",
                movimentacao
        );

        carregarDadosFormulario(model);

        return "financeiro/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
                "movimentacao",
                service.findById(id)
        );

        carregarDadosFormulario(model);

        return "financeiro/cadastro";
    }

    @PostMapping("/salvar")
    public String salvar(
            @ModelAttribute("movimentacao")
            MovimentacaoFinanceira movimentacao,

            @RequestParam(required = false)
            Long pedidoId
    ) {

        if (movimentacao.getData() == null) {
            movimentacao.setData(LocalDate.now());
        }

        if (pedidoId != null) {
            movimentacao.setPedido(
                    pedidoRepository.findById(pedidoId)
                            .orElse(null)
            );
        } else {
            movimentacao.setPedido(null);
        }

        service.save(movimentacao);

        return "redirect:/financeiro/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluir(
            @PathVariable Long id
    ) {

        service.delete(id);

        return "redirect:/financeiro/listagem";
    }

    private void carregarDadosFormulario(Model model) {

        model.addAttribute(
                "tipos",
                TipoMovimentacao.values()
        );

        model.addAttribute(
                "categorias",
                CategoriaDespensa.values()
        );

        model.addAttribute(
                "pedidos",
                pedidoRepository.findAll()
        );
    }
}