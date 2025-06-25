package bdTrabalho.OngAplication.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table
public class Tratamentos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String clinica;

    @Column(nullable = false)
    private String tipo;

    @Column
    private String descricao;

    @Column(nullable = false)
    private LocalDateTime dataRealizacao;

    @Column(precision = 10, scale = 2)
    private BigDecimal custo;

    @Column
    private String observacoes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animais animal;

}
