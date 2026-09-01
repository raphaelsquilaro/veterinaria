package sp.senai.org.vetmark.dto.request;

import sp.senai.org.vetmark.model.entity.Cliente;
import sp.senai.org.vetmark.model.entity.Servico;
import sp.senai.org.vetmark.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

public record AgendamentoRequest(
        LocalDateTime dataHora,
        StatusAgendamento status,
        String observacoes,
        Cliente cliente,
        Servico servico
) {
}
