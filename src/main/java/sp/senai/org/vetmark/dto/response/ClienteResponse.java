package sp.senai.org.vetmark.dto.response;

import sp.senai.org.vetmark.model.entity.Pet;

import java.time.LocalDateTime;
import java.util.List;

public record ClienteResponse(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf,
        LocalDateTime dataCadastro,
        List<Pet> petList
) {
}
