package sp.senai.org.vetmark.dto.request;

public record ClienteRequest(
        String nome,
        String telefone,
        String email,
        String cpf
) {}
