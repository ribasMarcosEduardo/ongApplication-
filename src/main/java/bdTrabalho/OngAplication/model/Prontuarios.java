package bdTrabalho.OngAplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"animal", "doencas", "vacinas"})
@EqualsAndHashCode(exclude = {"animal", "doencas", "vacinas"})
@Entity
@Table
public class Prontuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String observacoesGerais;

    @Column
    private String alergias;

    @Column
    private String deficiencia;

    @Column(nullable = false, length = 1)
    private char castrado;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id")
    private Animais animal;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "Doenca_Prontuario",
            joinColumns = @JoinColumn(name = "prontuario_id"),
            inverseJoinColumns = @JoinColumn(name = "doenca_id")
    )
    private Set<Doencas> doencas = new HashSet<>();

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(
            name = "Vacina_Prontuario",
            joinColumns = @JoinColumn(name = "prontuario_id"),
            inverseJoinColumns = @JoinColumn(name = "vacina_id")
    )
    private Set<Vacinas> vacinas = new HashSet<>();
}
