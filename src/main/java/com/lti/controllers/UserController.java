package com.lti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.UserRegisterRequest;
import com.lti.entities.User;
import com.lti.services.UserService;
import com.lti.util.ResponseUtil;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserService userService;
    
    
    @PostMapping("/register")
    public  ResponseEntity<Object> registerNewUser(@RequestBody UserRegisterRequest user)throws Exception{
        return userService.registerNewUser(user);
    }
    
    @GetMapping("/get/{userId}")
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
    
    
    
    @GetMapping("/admin")
    public User createDefaultAdmin() throws Exception{
    	
    	return userService.initRoleAndUser();
    	
    }
    
    
    
    
}