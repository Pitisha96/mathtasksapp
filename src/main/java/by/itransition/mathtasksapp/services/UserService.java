package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.User;
import by.itransition.mathtasksapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService extends DefaultOAuth2UserService {
    private final UserRepository userRepository;
    private final RoleService roleService;

    @Autowired
    public UserService(UserRepository userRepository, RoleService roleService) {
        this.userRepository = userRepository;
        this.roleService = roleService;
    }

    public User save(User user){
        Optional<User> userFromDB = userRepository.findByUsername(user.getUsername());
        if(userFromDB.isPresent()){
            return userFromDB.get();
        }
        user.setRoles(Collections.singleton(roleService.getRoleById(1L)));
        return userRepository.save(user);
    }

    public List<User> getAll(){
        return userRepository.findAll();
    }

    public String keyByPrincipal(OAuth2User principal){
        return (String) (principal.getAttributes().containsKey("login")
                        ?"login"
                        :"email");
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
            user.setRoles(Collections.singleton(roleService.findRoleByName("ROLE_USER")));
            user.setPrincipalName(oAuth2User.getName());
            user = userRepository.save(user);
            return new DefaultOAuth2User(user.getAuthorities(),
                    user.getAttributes(),
                    "name");
        }
    }
}
