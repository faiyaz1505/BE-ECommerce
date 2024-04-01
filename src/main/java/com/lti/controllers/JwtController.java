package com.lti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.lti.dto.JwtRequest;
import com.lti.dto.JwtResponse;
import com.lti.services.JwtService;

@RestController
@CrossOrigin
public class JwtController {
    @Autowired
    private JwtService jwtService;


    @PostMapping("/authenticate")
    public JwtResponse createToken(@RequestBody JwtRequest jwtRequest) throws Exception {
    	System.out.println("JwtController.createToken()");
        return jwtService.createJwtToken(jwtRequest);
    }
}
