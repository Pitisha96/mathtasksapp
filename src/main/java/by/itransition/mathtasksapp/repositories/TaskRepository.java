package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Long countByOwner(User user);
    List<Task> findAllByOwner(User owner);
    Optional<Task> findByName(String name);
}
