package Sprint.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Zadanie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private String opis;
    private int points;
    private int waga;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private User user;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Project project;

    @OneToMany(mappedBy = "zadanie")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Sprint> sprintSet;
}
