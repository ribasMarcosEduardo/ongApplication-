package bdTrabalho.OngAplication.model;

import bdTrabalho.OngAplication.model.EMUN.Situacao;
import bdTrabalho.OngAplication.model.EMUN.TipoSolicitacao;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Solicitacoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoSolicitacao tipo;

    @Column(nullable = false)
    private LocalDateTime dataSolicitacao;

    @Column
    private String descricao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Situacao situacao;

    @Column(nullable = false, length = 1)
    private char prioridade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "funcionario_id")
    private Funcionarios funcionario;
}
