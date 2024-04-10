package com.lti.controllers;

import com.lti.entities.Cart;
import com.lti.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RequestMapping("/cart")
@RestController
public class CartController {

    @Autowired
    CartService cartService;

    @PreAuthorize("hasRole('User')")
    @GetMapping("/add/{productId}")
    public Cart addToCart(@PathVariable Integer productId){
        return cartService.addTocart(productId);

    }

    @PreAuthorize("hasRole('User')")
    @GetMapping("/details")
    public List<Cart> cartDetailsByUser(){
        return cartService.getCartDetailsByUser();
    }



}
