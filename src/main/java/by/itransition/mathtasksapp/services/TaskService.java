package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.User;

import java.util.List;

public interface TaskService {
    Long countByOwner(User user);
    List<Task> findAllByOwner(User user);
    Task save(Task task);
    Task getById(Long id);
    List<Task> getLastPublished();
}
