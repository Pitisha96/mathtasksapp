package by.itransition.mathtasksapp.services;

import by.itransition.mathtasksapp.models.Role;

import java.util.List;

public interface RoleService {
    Role getRoleById(long id);
    Role findRoleByName(String name);
    List<Role> findAll();
}
