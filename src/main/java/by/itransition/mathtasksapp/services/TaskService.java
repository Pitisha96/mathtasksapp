package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.*;

import java.util.List;

public interface TaskService {
    Long countByOwner(User user);
    List<Task> findAllByOwner(User user);
    Task save(Task task);
    Task getById(Long id);
    List<Task> getLastPublished(int limit);
    List<Task> getFirstRating(int limit);
    List<Task> searchTasks(String searchLine);
    List<Tag> getAllTagsByTaskId(Long id);
    List<Answer> getAllAnswersByTaskId(Long id);
    List<Image> getAllImagesByTaskId(Long id);
    Double getRatingByTask(Task task);
    Task addRatingByTask(Rating rating,Task task);
    List<Task> findAllByTagName(String tagName);
    void deleteTaskById(Long id);
}
