package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    Long countByOwner(User user);
    List<Task> findAllByOwner(User owner);
}
