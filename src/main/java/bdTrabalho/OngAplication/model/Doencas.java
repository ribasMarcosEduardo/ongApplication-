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
public class Doencas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String cid;

    @Column(nullable = false)
    private String nome;

    @ManyToMany(mappedBy = "doencas")
    private Set<Prontuarios> prontuarios = new HashSet<>();
}
