package sp.senai.org.vetmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.Agendamento;
import sp.senai.org.vetmark.repository.*;

import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/agendamento")
public class AgendamentoController {

    private final AgendamentoRepository repository;
    private final ClienteRepository clienteRepository;
    private final PetRepository petRepository;
    private final ServicoRepository servicoRepository;
    private final VeterinarioRepository veterinarioRepository;

    @GetMapping("/listagem")
    public String listarAgendamento(
            @RequestParam(defaultValue = "diario") String modo,
            @RequestParam(required = false) LocalDate data,
            Model model
    ) {

        if (data == null) {
            data = LocalDate.now();
        }

        LocalDate inicio;
        LocalDate fim;

        if ("semanal".equals(modo)) {

            inicio = data.with(
                    java.time.DayOfWeek.MONDAY
            );

            fim = inicio.plusDays(6);

        } else if ("mensal".equals(modo)) {

            inicio = data.withDayOfMonth(1);

            fim = data.withDayOfMonth(
                    data.lengthOfMonth()
            );

        } else {

            modo = "diario";

            inicio = data;
            fim = data;
        }

        List<Agendamento> agendamentos =
                repository.findAll()
                        .stream()
                        .filter(agendamento ->
                                agendamento.getDataHora() != null
                        )
                        .filter(agendamento -> {

                            LocalDate dataAgendamento =
                                    agendamento.getDataHora()
                                            .toLocalDate();

                            return !dataAgendamento.isBefore(inicio)
                                    && !dataAgendamento.isAfter(fim);
                        })
                        .toList();

        LocalDate anterior;
        LocalDate proxima;

        if ("diario".equals(modo)) {

            anterior = data.minusDays(1);
            proxima = data.plusDays(1);

        } else if ("semanal".equals(modo)) {

            anterior = data.minusWeeks(1);
            proxima = data.plusWeeks(1);

        } else {

            anterior = data.minusMonths(1);
            proxima = data.plusMonths(1);
        }

        model.addAttribute("agendamentos", agendamentos);

        model.addAttribute("modo", modo);

        model.addAttribute("data", data);

        model.addAttribute("inicio", inicio);

        model.addAttribute("fim", fim);

        model.addAttribute("anterior", anterior);

        model.addAttribute("proxima", proxima);

        return "agendamento/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastroAgendamento(Model model) {

        model.addAttribute("agendamento", new Agendamento());

        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("servicos", servicoRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());

        return "agendamento/cadastro";
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
                                        "Agendamento não encontrado"
                                )
                        );

        model.addAttribute("agendamento", agendamento);

        model.addAttribute("clientes", clienteRepository.findAll());
        model.addAttribute("pets", petRepository.findAll());
        model.addAttribute("servicos", servicoRepository.findAll());
        model.addAttribute("veterinarios", veterinarioRepository.findAll());

        return "agendamento/cadastro";
    }

    @PostMapping("/salvar")
    public String salvarAgendamento(
            @ModelAttribute("agendamento") Agendamento agendamento,
            @RequestParam Long clienteId,
            @RequestParam Long petId,
            @RequestParam Long servicoId,
            @RequestParam Long veterinarioId
    ) {

        agendamento.setCliente(
                clienteRepository.findById(clienteId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Cliente não encontrado"))
        );

        agendamento.setPet(
                petRepository.findById(petId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Pet não encontrado"))
        );

        agendamento.setServico(
                servicoRepository.findById(servicoId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Serviço não encontrado"))
        );

        agendamento.setVeterinario(
                veterinarioRepository.findById(veterinarioId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException("Veterinário não encontrado"))
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

        model.addAttribute(
                "veterinarios",
                veterinarioRepository.findAll()
        );
    }
}