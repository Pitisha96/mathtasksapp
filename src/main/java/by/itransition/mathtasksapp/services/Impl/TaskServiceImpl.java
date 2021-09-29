package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.User;
import by.itransition.mathtasksapp.repositories.TaskRepository;
import by.itransition.mathtasksapp.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Long countByOwner(User user) {
        return taskRepository.countByOwner(user);
    }

    @Override
    public List<Task> findAllByOwner(User user) {
        return taskRepository.findAllByOwner(user);
    }
}
