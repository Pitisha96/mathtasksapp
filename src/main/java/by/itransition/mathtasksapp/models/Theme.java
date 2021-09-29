package by.itransition.mathtasksapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "themes")
@Data
@NoArgsConstructor
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public Theme(long id) {
        this.id = id;
    }

    @Transient
    @OneToMany(mappedBy = "theme",fetch = FetchType.LAZY)
    private Set<Task> tasks;
}
