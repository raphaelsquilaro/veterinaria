package sp.senai.org.vetmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import sp.senai.org.vetmark.model.entity.Pet;
import sp.senai.org.vetmark.repository.ClienteRepository;
import sp.senai.org.vetmark.service.PetService;

@Controller
@RequiredArgsConstructor
@RequestMapping("/pet")
public class PetController {

    private final PetService petService;
    private final ClienteRepository clienteRepository;

    @GetMapping("/listagem")
    public String listarPets(Model model) {
        model.addAttribute("pets", petService.findAll());
        return "pet/listagem";
    }

    @GetMapping("/cadastro")
    public String cadastroPet(Model model) {
        model.addAttribute("pet", new Pet());
        model.addAttribute("clientes", clienteRepository.findAll());

        return "pet/cadastro";
    }

    @GetMapping("/editar/{id}")
    public String editarPet(
            @PathVariable Long id,
            Model model
    ) {
        model.addAttribute("pet", petService.findById(id));
        model.addAttribute("clientes", clienteRepository.findAll());

        return "pet/cadastro";
    }

    @PostMapping("/salvar")
    public String salvarPet(@ModelAttribute("pet") Pet pet) {
        petService.save(pet);
        return "redirect:/pet/listagem";
    }

    @GetMapping("/excluir/{id}")
    public String excluirPet(@PathVariable Long id) {
        petService.delete(id);
        return "redirect:/pet/listagem";
    }
}