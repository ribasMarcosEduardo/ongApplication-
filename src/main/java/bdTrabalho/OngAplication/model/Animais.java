package bdTrabalho.OngAplication.model;

import bdTrabalho.OngAplication.model.ENUM.PorteAnimal;
import bdTrabalho.OngAplication.model.ENUM.TipoAnimal;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// Anotações corrigidas
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"prontuario", "tratamentos"})
@EqualsAndHashCode(exclude = {"prontuario", "tratamentos"})
@Entity
public class Animais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String descricao;

    @Column(nullable = false)
    private String raca;

    private Integer idade;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoAnimal tipo;

    @Column(length = 1024)
    private String foto;

    @Column(nullable = false)
    private Double peso;

    @Column(nullable = false, length = 1)
    private char sexo;

    @Column(nullable = false, length = 1)
    private char situacao;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PorteAnimal porte;

    @Column(nullable = false)
    private LocalDateTime dataChegada;

    @Column
    private LocalDateTime dataNascimento;

    @Column(nullable = false)
    private String cor;

    @Column(length = 512)
    private String historia;

    @OneToOne(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Prontuarios prontuario;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Tratamentos> tratamentos = new ArrayList<>();

}