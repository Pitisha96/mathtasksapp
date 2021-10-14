package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.Answer;
import by.itransition.mathtasksapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Long> {
    List<Answer> findAllByTask(Task task);
}
