package sp.senai.org.vetmark.dto.request;

import java.time.LocalDateTime;

public record HistoricoClinicoRequest(
        LocalDateTime dataConsulta,
        String queixaPrincipal,
        String diagnostico,
        String tratamento,
        String medicacao,
        String observacoes,
        Long petId,
        Long veterinarioId,
        Long agendamentoId
) {
}
