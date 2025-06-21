package bdTrabalho.OngAplication.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private Set<Doenca> doencas = new HashSet<>();
}
