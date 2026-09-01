package sp.senai.org.vetmark.dto.response;

import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.model.entity.Servico;
import sp.senai.org.vetmark.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoResponse(
        Long id,
        LocalDateTime dataHora,
        StatusAgendamento status,
        String observacoes,
        Cliente cliente,
        Servico servico
) {
}
