package sp.senai.org.vetmark.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import sp.senai.org.vetmark.model.entity.Agendamento;
import sp.senai.org.vetmark.model.entity.MovimentacaoFinanceira;
import sp.senai.org.vetmark.model.entity.Pedido;
import sp.senai.org.vetmark.repository.AgendamentoRepository;
import sp.senai.org.vetmark.repository.MovimentacaoFinanceiraRepository;
import sp.senai.org.vetmark.repository.PedidoRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class DashboardController {

    private final AgendamentoRepository agendamentoRepository;
    private final PedidoRepository pedidoRepository;
    private final MovimentacaoFinanceiraRepository movimentacaoRepository;

    @GetMapping("/dashboard")
    public String dashboard(Model model) {

        LocalDate hoje = LocalDate.now();

        YearMonth mesAtual = YearMonth.now();

        LocalDate inicioMes = mesAtual.atDay(1);
        LocalDate fimMes = mesAtual.atEndOfMonth();


        // ==========================================
        // AGENDAMENTOS DE HOJE
        // ==========================================

        List<Agendamento> agendamentosHoje =
                agendamentoRepository.findAll()
                        .stream()
                        .filter(agendamento ->
                                agendamento.getDataHora() != null
                                        &&
                                        agendamento.getDataHora().toLocalDate()
                                                .equals(hoje)
                        )
                        .toList();

        long quantidadeAgendamentos =
                agendamentosHoje.size();


        // ==========================================
        // PEDIDOS / FATURAMENTO DO MÊS
        // ==========================================

        List<Pedido> pedidos =
                pedidoRepository.findAll()
                        .stream()
                        .filter(pedido ->
                                pedido.getDataPedido() != null
                                        &&
                                        !pedido.getDataPedido()
                                                .toLocalDate()
                                                .isBefore(inicioMes)
                                        &&
                                        !pedido.getDataPedido()
                                                .toLocalDate()
                                                .isAfter(fimMes)
                        )
                        .toList();

        BigDecimal faturamentoMes =
                pedidos.stream()
                        .map(Pedido::getValorTotal)
                        .filter(valor -> valor != null)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        // ==========================================
        // GASTOS DO MÊS
        // ==========================================

        List<MovimentacaoFinanceira> movimentacoes =
                movimentacaoRepository.findAll()
                        .stream()
                        .filter(movimentacao ->
                                movimentacao.getData() != null
                                        &&
                                        !movimentacao.getData()
                                                .isBefore(inicioMes)
                                        &&
                                        !movimentacao.getData()
                                                .isAfter(fimMes)
                        )
                        .toList();

        BigDecimal gastosMes =
                movimentacoes.stream()
                        .filter(movimentacao ->
                                movimentacao.getTipo() != null
                                        &&
                                        movimentacao.getTipo()
                                                .name()
                                                .equals("SAIDA")
                        )
                        .map(MovimentacaoFinanceira::getValor)
                        .filter(valor -> valor != null)
                        .reduce(
                                BigDecimal.ZERO,
                                BigDecimal::add
                        );


        // ==========================================
        // LUCRO
        // ==========================================

        BigDecimal lucroMes =
                faturamentoMes.subtract(gastosMes);


        // ==========================================
        // ENVIAR PARA O THYMELEAF
        // ==========================================

        model.addAttribute(
                "quantidadeAgendamentos",
                quantidadeAgendamentos
        );

        model.addAttribute(
                "faturamentoMes",
                faturamentoMes
        );

        model.addAttribute(
                "gastosMes",
                gastosMes
        );

        model.addAttribute(
                "lucroMes",
                lucroMes
        );

        return "dashboard";
    }
}