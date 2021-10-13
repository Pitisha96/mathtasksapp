package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.User;
import by.itransition.mathtasksapp.repositories.TaskRepository;
import by.itransition.mathtasksapp.services.TaskService;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EntityManager entityManager;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, EntityManager entityManager) {
        this.taskRepository = taskRepository;
        this.entityManager = entityManager;
    }

    @Override
    public Long countByOwner(User user) {
        return taskRepository.countByOwner(user);
    }

    @Override
    public List<Task> findAllByOwner(User user) {
        return taskRepository.findAllByOwner(user);
    }

    @Override
    public Task save(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Task getById(Long id) {
        return taskRepository.getById(id);
    }

    @Override
    public List<Task> getLastPublished(int limit) {
        return taskRepository.findLastPublished(limit);
    }

    @Override
    public List<Task> getFirstRating(int limit) {
        return taskRepository.findTopRating(limit);
    }

    @Override
    public List<Task> searchTasks(String searchLine) {
        SearchSession searchSession = Search.session(entityManager);
        SearchResult<Task> result = searchSession.search(Task.class)
                .where(f->f.match()
                    .fields("name","content")
                    .matching(searchLine))
                .fetchAll();
        return result.hits();
    }
}
