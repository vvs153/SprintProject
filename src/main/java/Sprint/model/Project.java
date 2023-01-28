package Sprint.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nazwa;
    private String opis;

    @OneToMany(mappedBy = "project")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Set<Task> task2Set;

    @ManyToMany(mappedBy = "projects")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
private Set<User> users = new HashSet<>();

}
