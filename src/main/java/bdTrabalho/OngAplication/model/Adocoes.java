package bdTrabalho.OngAplication.Model;

import bdTrabalho.OngAplication.Model.EMUN.SituacaoAdocao;
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
public class Adocoes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime dataAdocao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SituacaoAdocao situacao;

    @Column
    private String observacoes;

    @Column
    private String motivoReprovacao;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuarios usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animais animal;
}
