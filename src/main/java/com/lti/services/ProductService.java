package com.lti.services;


import com.lti.configuration.JwtRequestFilter;
import com.lti.entities.Cart;
import com.lti.entities.User;
import com.lti.repositories.CartRepository;
import com.lti.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entities.Product;
import com.lti.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productDao;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CartRepository cartRepository;


    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }

    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    public String deleteByProductId(Integer productId) {
        try {
            productDao.deleteById(productId);
        } catch (Exception e) {
            System.out.println("HANDLE " + e);

        }
        return "Hi " + productId;

    }

    public Product getProductById(Integer productId) {

        Product product = productDao.findById(productId).get();
        System.out.println(product.getProductId());
        return product;

    }

    public List<Product> getProductDetails(boolean isSingleProductCheckout, Integer productId) {
        System.out.println("get Product details");
        if (isSingleProductCheckout) {
            List<Product> list = new ArrayList<>();
            Product product = productDao.findById(productId).get();
            list.add(product);
            return list;
        } else {
// we are going to checkout entire cart
            String username = JwtRequestFilter.CURRENT_USER;
            User user = userRepository.findById(username).get();
            List<Cart> carts = cartRepository.findByUser(user);

            return carts.stream().map(x -> x.getProduct()).collect(Collectors.toList());
        }

    }

	public List<Product> getProductAvailableStockAsZero(){
		return productDao.findAll().stream().filter(p->p.getAvailableStock()!=null && p.getAvailableStock()<=0).collect(Collectors.toList());
	}

	public Product updateAvailableStock(Product product){
		Product product1=productDao.findById(product.getProductId()).get();
		product1.setAvailableStock(product.getAvailableStock());
		return productDao.save(product1);
	}

}
