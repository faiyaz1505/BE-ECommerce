package com.lti.util;

import com.lti.entities.Product;
import com.lti.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class UpdateQuantity {
    @Autowired
    private ProductRepository productRepository;

    public void updateOrderQuantity(Integer productId,Integer productQty){
        Product product=productRepository.findById(productId).get();
        product.setAvailableStock(product.getAvailableStock()-productQty);
        productRepository.save(product);
    }
}
