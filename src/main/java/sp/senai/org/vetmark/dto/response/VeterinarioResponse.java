package sp.senai.org.vetmark.dto.response;

public record VeterinarioResponse(
        Long id,
        String nome,
        String telefone,
        String email,
        String cpf,
        String crmv,
        String especialidade,
        Boolean ativo
) {
}
