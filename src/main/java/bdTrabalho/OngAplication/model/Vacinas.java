package bdTrabalho.OngAplication.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "prontuarios")
@EqualsAndHashCode(exclude = "prontuarios")
@Entity
@Table
public class Vacinas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String codigo;

    @ManyToMany(mappedBy = "vacinas")
    private Set<Prontuarios> prontuarios = new HashSet<>();
}
