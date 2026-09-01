package sp.senai.org.vetmark.dto.request;

import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.model.enums.EspeciePet;

import java.time.LocalDate;

public record PetRequest(
        String nome,
        EspeciePet especie,
        String raca,
        LocalDate dataNascimento,
        Cliente cliente
) {
}
