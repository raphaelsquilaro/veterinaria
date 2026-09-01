package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.Servico;
import sp.senai.org.vetmark.repository.ServicoRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/servico")
public class ServicoController {

    private final ServicoRepository repository;

    @GetMapping("/listagem")
    public String listarServico(Model model) {

        model.addAttribute(
                "servicos",
                repository.findAll()
        );

        return "";
    }

    @GetMapping("/cadastro")
    public String cadastroServico(Model model) {

        model.addAttribute(
                "servico",
                new Servico()
        );

        return "";
    }

    @GetMapping("/editar/{id}")
    public String editarServico(
            @PathVariable Long id,
            Model model
    ) {
        Servico servico =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Serviço não Encontrado"
                                )
                        );

        model.addAttribute(
                "servico",
                servico
        );

        return "";
    }

    @PostMapping("/salvar")
    public String salvarServico(
            @Valid @ModelAttribute Servico servico,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "";
        }

        repository.save(servico);

        return "redirect:";
    }

    @GetMapping("/excluir/{id}")
    public String excluirServico(
            @PathVariable Long id
    ) {
        repository.deleteById(id);

        return "";
    }
}
