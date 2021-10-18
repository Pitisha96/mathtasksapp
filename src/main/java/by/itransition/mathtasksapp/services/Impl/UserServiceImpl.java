package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.Role;
import by.itransition.mathtasksapp.models.Task;
import by.itransition.mathtasksapp.models.User;
import by.itransition.mathtasksapp.repositories.UserRepository;
import by.itransition.mathtasksapp.services.RoleService;
import by.itransition.mathtasksapp.services.TaskService;
import by.itransition.mathtasksapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl extends DefaultOAuth2UserService implements UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final TaskService taskService;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, TaskService taskService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.taskService = taskService;
    }

    @Override
    public User save(User user){
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB.isPresent()){
            return userFromDB.get();
        }
        user.setRoles(Collections.singletonList(roleService.getRoleById(1L)));
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllByRoleId(Long id) {
        return userRepository.getAllByRoleId(id);
    }

    @Override
    public String keyByPrincipal(OAuth2User principal){
        return (String) (principal.getAttributes().containsKey("login")
                        ?"login"
                        :"email");
    }

    @Override
    public Long countPublishedTasks(User user) {
        return taskService.countByOwner(user);
    }

    @Override
    public Long countSolved(User user) {
        return userRepository.countSolvedTasks(user.getId());
    }

    @Override
    public User getById(long id) {
        return userRepository.getById(id);
    }

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        Map<String,Object> attributes = oAuth2User.getAttributes();
        Optional<User> userFromDB = userRepository.
                findByUsername((String) attributes.get(keyByPrincipal(oAuth2User)));
        if(userFromDB.isPresent()){
            return new DefaultOAuth2User(userFromDB.get().getAuthorities(),
                    userFromDB.get().getAttributes(),
                    "name");
        }else{
            User user = new User();
            user.setUsername((String) attributes.get(keyByPrincipal(oAuth2User)));
            user.setRoles(Collections.singletonList(roleService.getRoleById(1)));
            user.setPrincipalName(oAuth2User.getName());
            user = userRepository.save(user);
            return new DefaultOAuth2User(user.getAuthorities(),
                    user.getAttributes(),
                    "name");
        }
    }
    @Override
    public void addSolved(Long idUser, Task task) {
        User user= userRepository.getById(idUser);
        List<Task> solvedTask = user.getSolvedTasks();
        solvedTask.add(task);
        user.setSolvedTasks(solvedTask);
        userRepository.save(user);
    }

    @Override
    public void addVoted(Long idUser, Task task) {
        User user = userRepository.getById(idUser);
        List<Task> votedTasks = user.getVotedTasks();
        votedTasks.add(task);
        user.setVotedTasks(votedTasks);
        userRepository.save(user);
    }
}
