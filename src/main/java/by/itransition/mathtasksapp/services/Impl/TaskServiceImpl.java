package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.*;
import by.itransition.mathtasksapp.repositories.TaskRepository;
import by.itransition.mathtasksapp.services.*;
import org.hibernate.search.engine.search.query.SearchResult;
import org.hibernate.search.mapper.orm.Search;
import org.hibernate.search.mapper.orm.session.SearchSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.LinkedList;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {
    private final TaskRepository taskRepository;
    private final EntityManager entityManager;
    private final AnswerService answerService;
    private final ImageService imageService;
    private final RatingService ratingService;
    private final TagService tagService;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository, EntityManager entityManager,
                           AnswerService answerService, ImageService imageService,
                           RatingService ratingService, TagService tagService) {
        this.taskRepository = taskRepository;
        this.entityManager = entityManager;
        this.answerService = answerService;
        this.imageService = imageService;
        this.ratingService = ratingService;
        this.tagService = tagService;
    }

    @Override
    public Long countByOwner(User user) {
        return taskRepository.countByOwner(user);
    }

    @Override
    public List<Task> findAllByOwner(User user) {
        return taskRepository.findAllByOwnerOrderByIdAsc(user);
    }

    @Override
    public List<Task> findAllByTagName(String tagName) {
        List<Task> result = new LinkedList<>();
        List<Task> tasks = taskRepository.findAll();
        tasks.forEach(task -> {
            if(task.getTags().contains(tagService.findByName(tagName))){
                result.add(task);
            }
        });
        return result;
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

    @Override
    public List<Tag> getAllTagsByTaskId(Long id) {
        return taskRepository.getById(id).getTags();
    }

    @Override
    public List<Answer> getAllAnswersByTaskId(Long id) {
        return answerService.getAllByTaskId(id);
    }

    @Override
    public List<Image> getAllImagesByTaskId(Long id) {
        return imageService.getAllByTaskId(id);
    }

    @Override
    public Double getRatingByTask(Task task) {
        return ratingService.getRatingByTask(task);
    }

    @Override
    public Task addRatingByTask(Rating rating,Task task) {
        rating.setTask(task);
        ratingService.save(rating);
        System.out.println(ratingService.getRatingByTask(task));
        task.setRating(ratingService.getRatingByTask(task));
        return taskRepository.save(task);
    }
}
