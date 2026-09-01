package sp.senai.org.vetmark.dto.response;

import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.model.enums.EspeciePet;

import java.time.LocalDate;

public record PetResponse(
        Long id,
        String nome,
        EspeciePet especie,
        String raca,
        LocalDate dataNascimento,
        Cliente cliente
) {
}
