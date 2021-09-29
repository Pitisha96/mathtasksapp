package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.User;
import org.springframework.security.oauth2.core.user.OAuth2User;

public interface UserService{
    User save(User user);
    String keyByPrincipal(OAuth2User principal);
    Long countPublishedTasks(User user);
    Long countSolved(User user);
    User getById(long id);
}
