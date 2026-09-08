package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.model.entity.Produto;
import sp.senai.org.vetmark.service.ProdutoService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/produto")
public class ProdutoController {

    private final ProdutoService produtoService;

    @GetMapping("/listagem")
    public String listarProdutos(Model model) {

        model.addAttribute(
                "produtos",
                produtoService.findAll()
        );

        return "produto/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastroProduto(Model model) {

        model.addAttribute(
                "produto",
                new Produto()
        );

        return "produto/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String editarProduto(
            @PathVariable Long id,
            Model model
    ) {

        model.addAttribute(
                "produto",
                produtoService.findById(id)
        );

        return "produto/cadastro";
    }

    @PostMapping("/salvar")
    public String salvarProduto(
            @Valid @ModelAttribute("produto") Produto produto,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "produto/cadastro";
        }

        produtoService.save(produto);

        return "redirect:/produto/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluirProduto(
            @PathVariable Long id
    ) {

        produtoService.delete(id);

        return "redirect:/produto/listagem";
    }
}