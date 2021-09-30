package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag,Long> {
    Optional<Tag> findByName(String name);
    @Query(value = "select * from tags order by id desc limit 12",nativeQuery = true)
    List<Tag> findLast12Tags();
}
