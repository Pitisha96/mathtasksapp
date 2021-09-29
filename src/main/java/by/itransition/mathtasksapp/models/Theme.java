package by.itransition.mathtasksapp.models;

import lombok.Data;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "themes")
@Data
public class Theme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    @Transient
    @OneToMany(mappedBy = "theme",fetch = FetchType.LAZY)
    private Set<Task> tasks;
}
