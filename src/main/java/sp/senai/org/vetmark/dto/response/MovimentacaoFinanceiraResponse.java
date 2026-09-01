package sp.senai.org.vetmark.dto.response;

import sp.senai.org.vetmark.model.entity.Pedido;
import sp.senai.org.vetmark.model.enums.CategoriaDespensa;
import sp.senai.org.vetmark.model.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

public record MovimentacaoFinanceiraResponse(
        Long id,
        String descricao,
        BigDecimal valor,
        TipoMovimentacao tipo,
        CategoriaDespensa categoria,
        LocalDate data,
        Pedido pedido
) {
}
