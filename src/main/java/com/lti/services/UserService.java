package com.lti.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lti.dto.UserRegisterRequest;
import com.lti.entities.Role;
import com.lti.entities.User;
import com.lti.repositories.UserRepository;
import com.lti.util.ResponseUtil;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    public  ResponseEntity<Object> registerNewUser(UserRegisterRequest user)throws Exception{
        Optional userObject=
                userRepository.findById(user.getUserEmail());
        if(userObject.isPresent()){
            throw new RuntimeException("User already present");
        }
        User user1=new User();
        user1.setUserName(user.getUserEmail().toLowerCase());
        user1.setUserFirstName(user.getUserFirstName());
        user1.setUserLastName(user.getUserLastName());
        user1.setUserMobileNo(user.getUserMobileNo());
        user1.setUserCity(user.getUserCity());
        Set<Role> role=new HashSet<>();
        role.add(new Role("user","It is a user role"));
        user1.setRole(role);
        user1.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        userRepository.save(user1);
        ResponseEntity<Object> response = ResponseUtil.getResponse(user, HttpStatus.CREATED,"user is saved","true" );
        return response;
    }
    
    public User getUser(String userId) {
    	 return userRepository.findById(userId).get();
    }
    
    public User initRoleAndUser() throws Exception {
		Role role1 = new Role("admin", "Admin Roles ");
		
		User user = new User();
		user.setUserFirstName("admin");
		user.setUserLastName("bhai");
		user.setUserName("admin001@gmail.com");
		user.setUserPassword(passwordEncoder.encode("Admin123"));
		user.setUserCity("Mumbai");
		user.setUserMobileNo("7738886156");
		Set<Role> set1 = new HashSet();
		set1.add(role1);
		user.setRole(set1);
		if(userRepository.existsById(user.getUserName()))
			throw new RuntimeException("Admin already present");
		return userRepository.save(user);
	}
   

    
}
