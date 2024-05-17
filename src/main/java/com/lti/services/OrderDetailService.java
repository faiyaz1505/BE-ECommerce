package com.lti.services;

import com.lti.configuration.JwtRequestFilter;
import com.lti.dto.OrderInput;
import com.lti.dto.OrderProductQuantity;
import com.lti.entities.Cart;
import com.lti.entities.OrderDetail;
import com.lti.entities.Product;
import com.lti.entities.User;
import com.lti.repositories.CartRepository;
import com.lti.repositories.OrderDetailRepository;
import com.lti.repositories.ProductRepository;
import com.lti.repositories.UserRepository;
import com.lti.util.UpdateQuantity;
import org.apache.tomcat.jni.Proc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    @Autowired
    private CartRepository cartRepository;
    private static final String ORDER_PLACED="Placed";

    public void placeOrder(OrderInput orderInput, boolean isSingleProductCheckout) {
        List<OrderProductQuantity> productQuantityList = orderInput.getOrderProductQuantityList();

        for (OrderProductQuantity o: productQuantityList) {
            Product product = productRepository.findById(o.getProductId()).get();

            String currentUser = JwtRequestFilter.CURRENT_USER;
            User user = userRepository.findById(currentUser).get();

            OrderDetail orderDetail = new OrderDetail(
                    orderInput.getFullName(),
                    orderInput.getFullAddress(),
                    orderInput.getContactNumber(),
                    orderInput.getAlternateContactNumber(),
                    ORDER_PLACED,
                    product.getProductDiscountedPrice() * o.getQuantity(),
                    product,
                    user

            );

            // empty the cart.
            if(!isSingleProductCheckout) {
                List<Cart> carts = cartRepository.findByUser(user);
                carts.stream().forEach(x -> cartRepository.deleteById(x.getCartId()));
            }

            orderDetailRepository.save(orderDetail);
            updateQuantity.updateOrderQuantity(o.getProductId(),o.getQuantity());
        }
    }

    public void markOrderAsDelivered(Integer orderId) {
        OrderDetail orderDetail = orderDetailRepository.findById(orderId).get();

        if(orderDetail != null) {
            orderDetail.setOrderStatus("Delivered");
            orderDetailRepository.save(orderDetail);
        }

    }


    public List<OrderDetail> getOrderDetails(){
        String username=JwtRequestFilter.CURRENT_USER;
        User user=userRepository.findById(username).get();
        return orderDetailRepository.findByUser(user);
    }

    public List<OrderDetail> getAllOrderDetails(String status){
        List<OrderDetail> orderDetails=new ArrayList<>();
        if (status.equalsIgnoreCase("all")){
            orderDetailRepository.findAll().forEach(
                    x->orderDetails.add(x)
            );
        }else {
            orderDetailRepository.findByOrderStatus(status).forEach(
                    x->orderDetails.add(x)
            );
        }

        return orderDetails;
    }


    public void markAsDelivered(Integer orderId) {
        OrderDetail orderDetail=orderDetailRepository.findById(orderId).get();
        if (orderDetail!=null){
            orderDetail.setOrderStatus("Delivered");
            orderDetailRepository.save(orderDetail);
        }
    }
}
