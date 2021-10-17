package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.Rating;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingRepository extends JpaRepository<Rating,Long> {
    @Query("select avg(r.value) from Rating r where r.task.id=?1")
    Double getAvgRatingByTaskId(Long id);
}
