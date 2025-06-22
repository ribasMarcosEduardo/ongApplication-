package bdTrabalho.OngAplication.Model;

import bdTrabalho.OngAplication.Model.EMUN.PorteAnimal;
import bdTrabalho.OngAplication.Model.EMUN.Vacinas;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
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

    @Column(nullable = false)
    private String especie;

    @Column(nullable = false, length = 1024)
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

    private LocalDateTime dataNascimento;

    @Column(nullable = false)
    private String cor;

    @Column(length = 512)
    private String historia;

    @OneToOne(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Prontuarios prontuario;

    @OneToMany(mappedBy = "animal", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Tratamentos> tratamentos = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "Animal_Vacina",
            joinColumns = @JoinColumn(name = "animal_id"),
            inverseJoinColumns = @JoinColumn(name = "vacina_id")
    )
    private Set<Vacinas> vacinas = new HashSet<>();
}
