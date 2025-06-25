package bdTrabalho.OngAplication.Model;

import bdTrabalho.OngAplication.Model.EMUN.SituacaoCampanha;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Campanhas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataInicio;

    @Column(nullable = false)
    private LocalDateTime dataTermino;

    @Column
    private String meta;

    @Enumerated(EnumType.STRING)
    private SituacaoCampanha situacao;

    @Column(nullable = false)
    private String tipo;

    @Column(nullable = false)
    private String localizacao;

    @Column(precision = 10, scale = 2)
    private BigDecimal custo;

    @Column(precision = 10, scale = 2)
    private BigDecimal lucro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organizador_id")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    private Usuarios organizador;

}
