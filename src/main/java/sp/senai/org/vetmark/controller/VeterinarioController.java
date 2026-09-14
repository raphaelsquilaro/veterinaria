package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.model.entity.Veterinario;
import sp.senai.org.vetmark.service.VeterinarioService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/veterinario")
public class VeterinarioController {

    private final VeterinarioService veterinarioService;

    @GetMapping("/listagem")
    public String listarVeterinarios(Model model) {

        model.addAttribute(
                "veterinarios",
                veterinarioService.findAll()
        );

        return "veterinario/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastroVeterinario(Model model) {

        model.addAttribute(
                "veterinario",
                new Veterinario()
        );

        return "veterinario/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String editarVeterinario(
            @PathVariable Long id,
            Model model
    ) {

        Veterinario veterinario =
                veterinarioService.findById(id);

        model.addAttribute(
                "veterinario",
                veterinario
        );

        return "veterinario/cadastro";
    }

    @PostMapping("/salvar")
    public String salvarVeterinario(
            @Valid @ModelAttribute("veterinario")
            Veterinario veterinario,
            BindingResult result
    ) {

        if (result.hasErrors()) {
            return "veterinario/cadastro";
        }

        veterinarioService.save(veterinario);

        return "redirect:/veterinario/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluirVeterinario(
            @PathVariable Long id
    ) {

        veterinarioService.delete(id);

        return "redirect:/veterinario/listagem";
    }
}