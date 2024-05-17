package com.lti.controllers;

import com.lti.dto.CheckoutDto;
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
    @GetMapping({"/{productId}"})
    public Cart addToCart(@PathVariable(name = "productId") Integer productId) {
        return cartService.addToCart(productId);
    }

    @PreAuthorize("hasRole('User')")
    @DeleteMapping({"/{cartId}"})
    public void deleteCartItem(@PathVariable(name = "cartId") Integer cartId) {
        cartService.deleteCartItem(cartId);
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getCartDetails"})
    public List<Cart> getCartDetails() {
        return cartService.getCartDetails();
    }

    @PreAuthorize("hasRole('User')")
    @GetMapping({"/checkoutAmount"})
    public CheckoutDto checkoutAmount(){
        return cartService.checkoutAmount();
    }


}
