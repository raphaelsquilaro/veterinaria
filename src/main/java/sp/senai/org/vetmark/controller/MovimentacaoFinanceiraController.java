package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.MovimentacaoFinanceira;
import sp.senai.org.vetmark.repository.MovimentacaoFinanceiraRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/financeiro")
public class MovimentacaoFinanceiraController {

    private final MovimentacaoFinanceiraRepository repository;

    @GetMapping("/listagem")
    public String listarFinanceiro(Model model) {

        model.addAttribute(
                "financeiros",
                repository.findAll()
        );

        return "";
    }

    @GetMapping("/cadastro")
    public String cadastroFinanceiro(Model model) {

        model.addAttribute(
                "financeiro",
                new MovimentacaoFinanceira()
        );

        return "";
    }

    @GetMapping("/editar/{id}")
    public String editarFinanceiro(
            @PathVariable Long id,
            Model model
    ) {
        MovimentacaoFinanceira movimentacaoFinanceira =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Financeiro não Encontrado"
                                )
                        );

        model.addAttribute(
                "financeiro",
                movimentacaoFinanceira
        );

        return "";
    }

    @PostMapping("/salvar")
    public String salvarFinanceiro(
            @Valid @ModelAttribute MovimentacaoFinanceira movimentacaoFinanceira,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "";
        }

        repository.save(movimentacaoFinanceira);

        return "redirect:";
    }

    @GetMapping("/excluir/{id}")
    public String excluirFinanceiro(
            @PathVariable Long id
    ) {
        repository.deleteById(id);

        return "";
    }
}
