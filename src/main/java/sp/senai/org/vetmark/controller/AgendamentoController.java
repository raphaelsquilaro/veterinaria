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
import sp.senai.org.vetmark.repository.ClienteRepository;
import sp.senai.org.vetmark.repository.PetRepository;
import sp.senai.org.vetmark.repository.ServicoRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;

    @GetMapping("/listagem")
    public String listarAgendamento(Model model) {

        model.addAttribute(
                "agendamentos",
                repository.findAll()
        );

        return "agendamento/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastroAgendamento(Model model) {

        model.addAttribute(
                "agendamento",
                new Agendamento()
        );

        carregarDadosFormulario(model);

        return "agendamento/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String editarAgendamento(
            @PathVariable Long id,
            Model model
    ) {

        Agendamento agendamento = repository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Agendamento não encontrado"
                        )
                );

        model.addAttribute(
                "agendamento",
                agendamento
        );

        carregarDadosFormulario(model);

        return "agendamento/cadastro";
    }

    @PostMapping("/salvar")
    public String salvarAgendamento(
            @Valid @ModelAttribute("agendamento") Agendamento agendamento,
            BindingResult result,
            @RequestParam Long clienteId,
            @RequestParam Long petId,
            @RequestParam Long servicoId,
            Model model
    ) {

        if (result.hasErrors()) {

            carregarDadosFormulario(model);

            return "agendamento/cadastro";
        }

        agendamento.setCliente(
                clienteRepository.findById(clienteId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Cliente não encontrado"
                                )
                        )
        );

        agendamento.setPet(
                petRepository.findById(petId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pet não encontrado"
                                )
                        )
        );

        agendamento.setServico(
                servicoRepository.findById(servicoId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Serviço não encontrado"
                                )
                        )
        );

        repository.save(agendamento);

        return "redirect:/agendamento/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluirAgendamento(
            @PathVariable Long id
    ) {

        repository.deleteById(id);

        return "redirect:/agendamento/listagem";
    }

    private void carregarDadosFormulario(Model model) {

        model.addAttribute(
                "clientes",
                clienteRepository.findAll()
        );

        model.addAttribute(
                "pets",
                petRepository.findAll()
        );

        model.addAttribute(
                "servicos",
                servicoRepository.findAll()
        );
    }
}