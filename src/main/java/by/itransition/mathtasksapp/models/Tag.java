package by.itransition.mathtasksapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tags")
@Data
@NoArgsConstructor
@EqualsAndHashCode
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    public Tag(String name) {
        this.name = name;
    }

    @Transient
    @ManyToMany(mappedBy = "tags",fetch = FetchType.LAZY)
    private List<Task> tasks;
}
