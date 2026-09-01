package sp.senai.org.vetmark.dto.response;

import java.math.BigDecimal;

public record ServicoResponse(
        Long id,
        String nome,
        String descricao,
        BigDecimal valor,
        Boolean ativo
) {
}
