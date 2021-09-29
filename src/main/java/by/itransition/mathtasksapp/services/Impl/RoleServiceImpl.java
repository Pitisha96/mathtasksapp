package by.itransition.mathtasksapp.services.Impl;

import by.itransition.mathtasksapp.models.Role;
import by.itransition.mathtasksapp.repositories.RoleRepository;
import by.itransition.mathtasksapp.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    public final RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role getRoleById(long id){
        return roleRepository.getById(id);
    }

    public Role findRoleByName(String name){
        return roleRepository.findByName(name).get();
    }

    public List<Role> findAll(){
        return roleRepository.findAll();
    }
}
