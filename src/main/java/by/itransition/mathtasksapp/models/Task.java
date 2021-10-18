package by.itransition.mathtasksapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.FullTextField;
import org.hibernate.search.mapper.pojo.mapping.definition.annotation.Indexed;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "task_tags",
            joinColumns = @JoinColumn(name = "task_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id",referencedColumnName = "id")
    )
    private List<Tag> tags;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private User owner;

    @Transient
    @ManyToMany(mappedBy = "solvedTasks",fetch = FetchType.LAZY)
    private List<User> users;

    @Transient
    @ManyToMany(mappedBy = "votedTasks",fetch = FetchType.LAZY)
    private List<User> votedUsers;

    @Transient
    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY)
    private List<Image> images;

    @Transient
    @OneToMany(mappedBy = "task",fetch = FetchType.LAZY)
    private List<Answer> answers;

    @Transient
    @OneToMany(mappedBy = "task")
    private List<Rating> ratings;

    public Task(Long id){
        this.id=id;
    }

    public Task(String name, String content, Date published, Theme theme,
                List<Tag> tags, User owner) {
        this.name = name;
        this.content = content;
        this.published = published;
        this.theme = theme;
        this.tags = tags;
        this.owner = owner;
    }

    public String getFormatDate(){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd 'at' HH:mm:ss");
        return dateFormat.format(published);
    }
}
