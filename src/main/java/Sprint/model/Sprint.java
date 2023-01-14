package Sprint.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sprint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private LocalDate poczatek;
    private LocalDate koniec;
    private int sprintPoints;

    @ManyToOne
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Zadanie zadanie;

}
