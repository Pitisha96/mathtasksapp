package by.itransition.mathtasksapp.models;

import lombok.Data;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name="users")
@Data
public class User implements OAuth2User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String username;

    private String principalName;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name="user_id"),
            inverseJoinColumns =@JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Transient
    @OneToMany(mappedBy = "owner",fetch = FetchType.LAZY)
    private Set<Task> tasks;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_tasks",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "task_id")
    )
    private Set<Task> solvedTasks;

    @Override
    public Map<String, Object> getAttributes() {
        Map<String,Object> attributes = new HashMap<>();
        attributes.put("id",getId());
        attributes.put("username",getUsername());
        attributes.put("name",principalName);
        return attributes;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getName() {
        return principalName;
    }
}
