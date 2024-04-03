package com.lti.controllers;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.lti.entities.Role;
import com.lti.entities.User;
import com.lti.services.RoleService;

@RestController
@RequestMapping("/role")
@CrossOrigin
public class RoleController {
    @Autowired
    private RoleService roleService;
    
    
    @PostMapping("/new")
    public Role createRole(@RequestBody Role role){
        return roleService.createRole(role);
    }
    
    
}
