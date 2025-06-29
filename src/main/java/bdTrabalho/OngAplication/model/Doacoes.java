package bdTrabalho.OngAplication.model;


import bdTrabalho.OngAplication.model.ENUM.SituacaoDoacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Doacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(precision = 10, scale = 2)
    private BigDecimal valor;

    @Column
    private Integer quantidade;

    @Column(nullable = false)
    private String tipo;

    @Column
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoDoacao situacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "doador_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Usuarios doador;
}

