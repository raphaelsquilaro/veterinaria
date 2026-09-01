package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.Agendamento;
import sp.senai.org.vetmark.repository.AgendamentoRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoRepository repository;

    @GetMapping("/listagem")
    public String listarAgentamento(Model model) {

        model.addAttribute(
                "agendamentos",
                repository.findAll()
        );

        return "";
    }

    @GetMapping("/cadastro")
    public String cadastroAgendamento(Model model) {

        model.addAttribute(
                "agendamento",
                new Agendamento()
        );

        return "";
    }

    @GetMapping("/editar/{id}")
    public String editarAgendamento(
            @PathVariable Long id,
            Model model
    ) {
        Agendamento agendamento =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Agendamento não Encontrado"
                                )
                        );

        model.addAttribute(
                "agendamento",
                agendamento
        );

        return "";
    }

    @PostMapping("/salvar")
    public String salvarAgendamento(
            @Valid @ModelAttribute Agendamento agendamento,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "";
        }

        repository.save(agendamento);

        return "redirect:";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAgendamento(
            @PathVariable Long id
    ) {
        repository.deleteById(id);

        return "";
    }
}
