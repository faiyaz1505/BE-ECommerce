package com.example.services;

import com.example.entities.Role;
import com.example.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public Role createRole(Role role){
//        Role role1=new Role;
        return roleRepository.save(role);
    }
}
