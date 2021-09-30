package by.itransition.mathtasksapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name="tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private String content;

    private Date published;

    private double rating;

    @ManyToOne
    @JoinColumn(name = "theme_id")
    private Theme theme;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id")
    )
    private Set<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Transient
    @ManyToMany(mappedBy = "solvedTasks",fetch = FetchType.LAZY)
    private Set<User> users;

    public Task(String name, String content, Date published, Theme theme, Set<Tag> tags, User owner) {
        this.name = name;
        this.content = content;
        this.published = published;
        this.theme = theme;
        this.tags = tags;
        this.owner = owner;
    }
}
