package com.lti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entities.Role;
import com.lti.repositories.RoleRepository;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;


    public Role createRole(Role role){
//        Role role1=new Role;
        return roleRepository.save(role);
    }
    
    
    
}
