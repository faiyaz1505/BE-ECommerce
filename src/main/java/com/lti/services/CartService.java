package com.lti.services;

import com.lti.configuration.JwtRequestFilter;
import com.lti.entities.Cart;
import com.lti.entities.Product;
import com.lti.entities.User;
import com.lti.repositories.CartRepository;
import com.lti.repositories.ProductRepository;
import com.lti.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CartRepository cartRepository;

    public Cart addTocart(Integer productId){

        String currentUser = JwtRequestFilter.CURRENT_USER;
       Optional<Product> product=productRepository.findById(productId);

        Optional<User> user = userRepository.findById(currentUser);

        if(product.isPresent() && user.isPresent()){
            Cart cart=new Cart(user.get(),product.get());
            return cartRepository.save(cart);
        }
        throw new RuntimeException("User or Product is Null");
    }

    public List<Cart> getCartDetailsByUser(){
        String currentUserName=JwtRequestFilter.CURRENT_USER;

        User user=userRepository.findById(currentUserName).get();

        return cartRepository.findByUser(user);
    }



}

