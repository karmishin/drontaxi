package xyz.karmishin.drontaxiweb.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.karmishin.drontaxiweb.entities.Role;
import xyz.karmishin.drontaxiweb.repositories.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public List<Role> all() {
        return roleRepository.findAll();
    }

}
