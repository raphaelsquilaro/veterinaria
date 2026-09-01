package sp.senai.org.vetmark.model.entity;

import jakarta.persistence.*;
import lombok.*;
import sp.senai.org.vetmark.model.enums.CategoriaDespensa;
import sp.senai.org.vetmark.model.enums.TipoMovimentacao;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(
        name = "movimentacao_financeira",
        indexes = {
                @Index(
                        name = "idx_movimentacao_data",
                        columnList = "data"
                ),
                @Index(
                        name = "idx_movimentacao_tipo",
                        columnList = "tipo"
                )
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovimentacaoFinanceira {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            length = 200
    )
    private String descricao;

    @Column(
            nullable = false,
            precision = 15,
            scale = 2
    )
    private BigDecimal valor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoMovimentacao tipo;

    @Enumerated(EnumType.STRING)
    private CategoriaDespensa categoria;

    @Column(nullable = false)
    private LocalDate data;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pedido_id")
    private Pedido pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public TipoMovimentacao getTipo() {
        return tipo;
    }

    public void setTipo(TipoMovimentacao tipo) {
        this.tipo = tipo;
    }

    public CategoriaDespensa getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaDespensa categoria) {
        this.categoria = categoria;
    }

    public LocalDate getData() {
        return data;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }
}
