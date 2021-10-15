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

    @Column(name = "public_id")
    private String publicId;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    public Image(String url,String publicId,Task task){
        this.url=url;
        this.task=task;
        this.publicId=publicId;
    }
}
