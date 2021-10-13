package by.itransition.mathtasksapp.models;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@Table(name="images")
public class Image{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String url;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Image(String url,Task task){
        this.url=url;
        this.task=task;
    }
}
