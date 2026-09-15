package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.HistoricoClinico;
import sp.senai.org.vetmark.repository.AgendamentoRepository;
import sp.senai.org.vetmark.repository.PetRepository;
import sp.senai.org.vetmark.repository.VeterinarioRepository;
import sp.senai.org.vetmark.service.HistoricoClinicoService;

import java.time.LocalDateTime;

@Controller
@RequiredArgsConstructor
@RequestMapping("/historico-clinico")
public class HistoricoClinicoController {

    private final HistoricoClinicoService service;

    private final PetRepository petRepository;
    private final VeterinarioRepository veterinarioRepository;
    private final AgendamentoRepository agendamentoRepository;


    @GetMapping("/listagem")
    public String listarHistoricos(Model model) {

        model.addAttribute(
                "historicos",
                service.findAll()
        );

        return "historico-clinico/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastroHistorico(
            Model model
    ) {

        HistoricoClinico historico =
                new HistoricoClinico();

        historico.setDataConsulta(
                LocalDateTime.now()
        );

        model.addAttribute(
                "historico",
                historico
        );

        carregarDadosFormulario(model);

        return "historico-clinico/cadastro";
    }


    @GetMapping("/cadastro/pet/{petId}")
    public String cadastroHistoricoDoPet(
            @PathVariable Long petId,
            Model model
    ) {

        var pet = petRepository.findById(petId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Pet não encontrado"
                        )
                );

        HistoricoClinico historico =
                new HistoricoClinico();

        historico.setDataConsulta(
                LocalDateTime.now()
        );

        historico.setPet(pet);

        model.addAttribute(
                "historico",
                historico
        );

        carregarDadosFormulario(model);

        return "historico-clinico/cadastro";
    }


    @GetMapping("/editar/{id}")
    public String editarHistorico(
            @PathVariable Long id,
            Model model
    ) {

        HistoricoClinico historico =
                service.findById(id);

        model.addAttribute(
                "historico",
                historico
        );

        carregarDadosFormulario(model);

        return "historico-clinico/cadastro";
    }


    @PostMapping("/salvar")
    public String salvarHistorico(
            @Valid
            @ModelAttribute("historico")
            HistoricoClinico historico,
            BindingResult result,
            @RequestParam Long petId,
            @RequestParam Long veterinarioId,
            @RequestParam(required = false) Long agendamentoId,
            Model model
    ) {

        if (result.hasErrors()) {

            carregarDadosFormulario(model);

            return "historico-clinico/cadastro";
        }


        historico.setPet(
                petRepository.findById(petId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pet não encontrado"
                                )
                        )
        );


        historico.setVeterinario(
                veterinarioRepository.findById(veterinarioId)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Veterinário não encontrado"
                                )
                        )
        );


        if (agendamentoId != null) {

            historico.setAgendamento(
                    agendamentoRepository.findById(
                            agendamentoId
                    ).orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Agendamento não encontrado"
                            )
                    )
            );

        } else {

            historico.setAgendamento(null);
        }


        if (historico.getDataConsulta() == null) {

            historico.setDataConsulta(
                    LocalDateTime.now()
            );
        }


        service.save(historico);

        return "redirect:/historico-clinico/listagem";
    }


    @GetMapping("/excluir/{id}")
    public String excluirHistorico(
            @PathVariable Long id
    ) {

        service.delete(id);

        return "redirect:/historico-clinico/listagem";
    }


    private void carregarDadosFormulario(
            Model model
    ) {

        model.addAttribute(
                "pets",
                petRepository.findAll()
        );

        model.addAttribute(
                "veterinarios",
                veterinarioRepository.findAll()
        );

        model.addAttribute(
                "agendamentos",
                agendamentoRepository.findAll()
        );
    }
}