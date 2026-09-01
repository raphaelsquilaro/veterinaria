package sp.senai.org.vetmark.dto.request;

import java.math.BigDecimal;

public record ServicoRequest(
        String nome,
        String descricao,
        BigDecimal valor,
        Boolean ativo
) {
}
