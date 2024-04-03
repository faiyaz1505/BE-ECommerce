package com.lti.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lti.dto.JwtRequest;
import com.lti.dto.JwtResponse;
import com.lti.entities.User;
import com.lti.repositories.UserRepository;
import com.lti.util.JwtUtil;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private AuthenticationManager authenticationManager;


    public JwtResponse createJwtToken(JwtRequest jwtRequest) throws Exception {
        String userName=jwtRequest.getUserEmail().toLowerCase();
        String userPassword= jwtRequest.getUserPassword();
        authenticate(userName,userPassword);
        final UserDetails userDetails=loadUserByUsername(userName);
        String newGeneratedToken=jwtUtil.generateToken(userDetails);

        User user=userRepository.findById(userName).orElseThrow(()->new RuntimeException("Incorrect Email"));
        return new JwtResponse(user,newGeneratedToken);


    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findById(username).orElseThrow(()->new RuntimeException("Incorrect Email"));
        if (user!=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getUserName(),
                    user.getUserPassword(),
                    getAuthorities(user)
            );
        }else {
            throw new UsernameNotFoundException("userName is not valid");
        }
    }

    private Set getAuthorities(User user){
        Set authorities=new HashSet<>();
        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        });
        return authorities;
    }

    private void authenticate(String userName,String userPassword) throws RuntimeException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userName,userPassword));
        }catch (DisabledException e){
            throw new RuntimeException("user is disabled");
        }catch (BadCredentialsException e){
            throw new RuntimeException("Bad credential from user");
        }


    }
}