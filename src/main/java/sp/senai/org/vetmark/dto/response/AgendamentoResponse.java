package sp.senai.org.vetmark.dto.response;

import lombok.Builder;
import lombok.Data;
import sp.senai.org.vetmark.model.enums.StatusAgendamento;

import java.time.LocalDateTime;

@Data
@Builder
public class AgendamentoResponse {

    private Long id;

    private LocalDateTime dataHora;

    private StatusAgendamento status;

    private String observacoes;

    private Long clienteId;
    private String clienteNome;

    private Long petId;
    private String petNome;

    private Long veterinarioId;
    private String veterinarioNome;

    private Long servicoId;
    private String servicoNome;
}