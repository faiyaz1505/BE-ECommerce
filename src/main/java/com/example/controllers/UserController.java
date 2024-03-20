package com.example.controllers;

import com.example.entities.User;
import com.example.services.UserService;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    
    
    @PostMapping("/registerNewUser")
    public User registerNewUser(@RequestBody User user){
        return userService.registerNewUser(user);
    }
    
    @GetMapping("/getUserById/{userId}")
    public User getUser(@PathVariable String userId) {
    	return userService.getUser(userId);
    }
    
    @PreAuthorize("hasRole('User')")
    @GetMapping("/forUser")
    public String forUser(){
        return "for user";
    }

    @PreAuthorize("hasRole('Admin')")
    @GetMapping("/forAdmin")
    public String forAdmin(){
        return "for admin";
    }
}
