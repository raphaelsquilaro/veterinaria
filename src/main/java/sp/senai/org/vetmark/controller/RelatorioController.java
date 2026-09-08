package sp.senai.org.vetmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sp.senai.org.vetmark.model.entity.MovimentacaoFinanceira;
import sp.senai.org.vetmark.repository.MovimentacaoFinanceiraRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/relatorio")
public class RelatorioController {

    private final MovimentacaoFinanceiraRepository movimentacaoRepository;

    @GetMapping("/listagem")
    public String relatorio(
            @RequestParam(required = false) LocalDate dataInicio,
            @RequestParam(required = false) LocalDate dataFim,
            Model model
    ) {

        List<MovimentacaoFinanceira> movimentacoes =
                movimentacaoRepository.findAll();

        if (dataInicio != null) {
            movimentacoes = movimentacoes.stream()
                    .filter(m ->
                            !m.getData().isBefore(dataInicio)
                    )
                    .toList();
        }

        if (dataFim != null) {
            movimentacoes = movimentacoes.stream()
                    .filter(m ->
                            !m.getData().isAfter(dataFim)
                    )
                    .toList();
        }

        BigDecimal totalEntradas = movimentacoes.stream()
                .filter(m ->
                        m.getTipo().name().equals("ENTRADA")
                )
                .map(MovimentacaoFinanceira::getValor)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );

        BigDecimal totalSaidas = movimentacoes.stream()
                .filter(m ->
                        m.getTipo().name().equals("SAIDA")
                )
                .map(MovimentacaoFinanceira::getValor)
                .reduce(
                        BigDecimal.ZERO,
                        BigDecimal::add
                );

        BigDecimal saldo =
                totalEntradas.subtract(totalSaidas);

        model.addAttribute(
                "movimentacoes",
                movimentacoes
        );

        model.addAttribute(
                "dataInicio",
                dataInicio
        );

        model.addAttribute(
                "dataFim",
                dataFim
        );

        model.addAttribute(
                "totalEntradas",
                totalEntradas
        );

        model.addAttribute(
                "totalSaidas",
                totalSaidas
        );

        model.addAttribute(
                "saldo",
                saldo
        );

        return "relatorio/listagem";
    }
}