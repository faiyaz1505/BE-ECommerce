package com.lti.controllers;

import com.lti.dto.OrderInput;
import com.lti.entities.OrderDetail;
import com.lti.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/order")
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;

    @PreAuthorize("hasRole('User')")
    @PostMapping({"/placeOrder"})
    public void placeOrder(@RequestBody OrderInput orderInput){
        orderDetailService.placeOrder(orderInput);
    }


    @PreAuthorize("hasRole('User')")
    @GetMapping({"/getOrders"})
    public List<OrderDetail> getOrders(){
        return orderDetailService.getOrderDetails();
    }
}
