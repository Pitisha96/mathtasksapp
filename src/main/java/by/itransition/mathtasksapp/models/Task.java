package by.itransition.mathtasksapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@Indexed
@Table(name="tasks")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @FullTextField
    private String name;

    @FullTextField
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
    private List<User> users;

    @Transient
    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY)
    private List<Image> images;

    @Transient
    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY)
    private List<Answer> answers;

    public Task(Long id){
        this.id=id;
    }

    public Task(String name, String content, Date published, Theme theme,
                Set<Tag> tags, User owner) {
        this.name = name;
        this.content = content;
        this.published = published;
        this.theme = theme;
        this.tags = tags;
        this.owner = owner;
    }
}
