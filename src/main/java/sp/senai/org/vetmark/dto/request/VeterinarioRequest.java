package sp.senai.org.vetmark.dto.request;

public record VeterinarioRequest(
        String nome,
        String telefone,
        String email,
        String cpf,
        String crmv,
        String especialidade,
        Boolean ativo
) {
}
