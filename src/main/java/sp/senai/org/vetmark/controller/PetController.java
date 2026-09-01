package sp.senai.org.vetmark.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.exception.ResourceNotFoundException;
import sp.senai.org.vetmark.model.entity.Pet;
import sp.senai.org.vetmark.repository.PetRepository;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetRepository repository;

    @GetMapping("/listagem")
    public String listarPet(Model model) {

        model.addAttribute(
                "pet",
                repository.findAll()
        );

        return "";
    }

    @GetMapping("/cadastro")
    public String cadastroPet(Model model) {

        model.addAttribute(
                "pet",
                new Pet()
        );

        return "";
    }

    @GetMapping("/editar/{id}")
    public String editarPet(
            @PathVariable Long id,
            Model model
    ) {
        Pet pet =
                repository.findById(id)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Pet não Encontrado"
                                )
                        );

        model.addAttribute(
                "pet",
                pet
        );

        return "";
    }

    @PostMapping("/salvar")
    public String salvarPet(
            @Valid @ModelAttribute Pet pet,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            return "";
        }

        repository.save(pet);

        return "redirect:";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPet(
            @PathVariable Long id
    ) {
        repository.deleteById(id);

        return "";
    }
}
