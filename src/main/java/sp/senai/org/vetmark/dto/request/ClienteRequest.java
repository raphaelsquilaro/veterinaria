package sp.senai.org.vetmark.dto.request;

import sp.senai.org.vetmark.model.entity.Pet;

import java.time.LocalDateTime;
import java.util.List;

public record ClienteRequest(
        String nome,
        String telefone,
        String email,
        String cpf,
        LocalDateTime dataNascimento,
        List<Pet> pets
) {
}
