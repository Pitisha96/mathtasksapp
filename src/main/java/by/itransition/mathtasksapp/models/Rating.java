package by.itransition.mathtasksapp.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "ratings")
@Data
@NoArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    double value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "task_id")
    Task task;

    public Rating(Double value){
        this.value=value;
    }
}
