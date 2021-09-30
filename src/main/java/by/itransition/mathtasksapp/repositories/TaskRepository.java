package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Long countByOwner(User user);
    List<Task> findAllByOwner(User owner);
    Optional<Task> findByName(String name);
    @Query(value = "select * from tasks order by published desc limit 10",nativeQuery = true)
    List<Task> findLast10Published();
    @Query(value = "select * from tasks order by rating desc limit 10",nativeQuery = true)
    List<Task> findTop10Rating();
}
