package by.itransition.mathtasksapp.repositories;

import by.itransition.mathtasksapp.models.Image;
import by.itransition.mathtasksapp.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findAllByTask(Task task);
}
