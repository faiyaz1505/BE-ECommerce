package com.lti.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lti.entities.Product;
import com.lti.repositories.ProductRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
	
	@Autowired
    private ProductRepository productDao;



    public Product addNewProduct(Product product) {
        return productDao.save(product);
    }
    
    public List<Product> getAllProducts(){
    	return productDao.findAll();
    }
    public String deleteByProductId(Integer productId) {
    	try {
    	productDao.deleteById(productId);
    	}
    	catch(Exception e){
    		System.out.println("HANDLE "+e);
    		
    	}
    	return "Hi "+productId;
    	
    }
    
    public Product getProductById(Integer productId) {
    	
    	 Product product = productDao.findById(productId).get();
    	 System.out.println(product.getProductId());
    	 return product;
    	
    }

	public List<Product> getProductDetails(boolean isSingleProductCheckout,Integer productId){
		System.out.println("get Product details");
		if (isSingleProductCheckout){
			List<Product> list=new ArrayList<>();
			Product product=productDao.findById(productId).get();
			list.add(product);
			return list;
		}else {

		}
		return new ArrayList<>();
	}

}
