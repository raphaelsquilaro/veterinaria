package sp.senai.org.vetmark.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "produtos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            nullable = false,
            length = 150
    )
    private String nome;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(
            nullable = false,
            precision = 15,
            scale = 2
    )
    private BigDecimal preco;

    @Column(nullable = false)
    private Integer estoque;

    @Column(nullable = false)
    @Builder.Default
    private Boolean ativo = true;
}