package sp.senai.org.vetmark.dto.response;

import java.time.LocalDateTime;

public record HistoricoClinicoResponse(
        Long id,
        LocalDateTime dataConsulta,
        String queixaPrincipal,
        String diagnostico,
        String tratamento,
        String medicacao,
        String observacoes,

        Long petId,
        String petNome,

        Long veterinarioId,
        String veterinarioNome,

        Long agendamentoId
) {
}
