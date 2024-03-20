package com.example.services;


import com.example.entities.Role;
import com.example.entities.User;
import com.example.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User registerNewUser(User user){
        User user1=new User();
        user1.setUserName(user.getUserName());
        user1.setUserFirstName(user.getUserFirstName());
        user1.setUserLastName(user.getUserLastName());
        Set<Role> role=new HashSet<>();
        role.add(new Role("User","It is a user role"));
        user1.setRole(role);
        user1.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        return userRepository.save(user1);
    }
    
    public User getUser(String userId) {
    	 return userRepository.findById(userId).get();
    }
}
