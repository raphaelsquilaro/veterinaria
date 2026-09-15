package sp.senai.org.vetmark.dto.request;

import lombok.Data;
import sp.senai.org.vetmark.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

@Data
public class AgendamentoRequest {

    private LocalDateTime dataHora;

    private StatusAgendamento status;

    private String observacoes;

    private Long clienteId;

    private Long petId;

    private Long veterinarioId;

    private Long servicoId;
}