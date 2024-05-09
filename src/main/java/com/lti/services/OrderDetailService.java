package com.lti.services;

import com.lti.configuration.JwtRequestFilter;
import com.lti.dto.OrderInput;
import com.lti.dto.OrderProductQuantity;
import com.lti.entities.OrderDetail;
import com.lti.entities.Product;
import com.lti.entities.User;
import com.lti.repositories.OrderDetailRepository;
import com.lti.repositories.ProductRepository;
import com.lti.repositories.UserRepository;
import com.lti.util.UpdateQuantity;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {

    @Autowired
    private UpdateQuantity updateQuantity;
    @Autowired
    private OrderDetailRepository orderDetailRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    private static final String ORDER_PLACED="Placed";

    public void placeOrder(OrderInput orderInput){
        List<OrderProductQuantity> orderProductQuantityList=orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o:orderProductQuantityList) {
            Product product=productRepository.findById(o.getProductId()).get();
            String userName=JwtRequestFilter.CURRENT_USER;
            User user=userRepository.findById(userName).get();
            OrderDetail orderDetail=new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice()*o.getQuantity(),
                    product,
                    user
                    );
            orderDetailRepository.save(orderDetail);
            updateQuantity.updateOrderQuantity(o.getProductId(),o.getQuantity());
        }
    }


    public List<OrderDetail> getOrderDetails(){
        String username=JwtRequestFilter.CURRENT_USER;
        User user=userRepository.findById(username).get();
        return orderDetailRepository.findByUser(user);
    }
}
