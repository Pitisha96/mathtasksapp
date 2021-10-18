package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.Role;
import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.List;

public interface UserService{
    User save(User user);
    List<User> getAllByRoleId(Long id);
    String keyByPrincipal(OAuth2User principal);
    Long countPublishedTasks(User user);
    Long countSolved(User user);
    User getById(long id);
    void addSolved(Long idUser, Task task);
    void addVoted(Long idUser,Task task);
}
