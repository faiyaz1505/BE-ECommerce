package com.lti.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.lti.entities.ImageModel;
import com.lti.entities.Product;
import com.lti.services.ProductService;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
    private ProductService productService;

    @PreAuthorize("hasRole('admin')")
    @PostMapping(value = {"/add"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public Product addNewProduct(@RequestPart("product") Product product,
                                 @RequestPart("imageFile") MultipartFile[] file) {
        try {
        	System.out.println("ProductController.addNewProduct()");
            Set<ImageModel> images = uploadImage(file);
            product.setProductImages(images);
            return productService.addNewProduct(product);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }

    }
    
//    @PreAuthorize("hasRole('admin')")
    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductById(@PathVariable Integer productId) {
    	
    	Product product = productService.getProductById(productId);
    	
    	return new ResponseEntity<Product>(product,HttpStatus.OK);
    }

    public Set<ImageModel> uploadImage(MultipartFile[] multipartFiles) throws IOException {
        Set<ImageModel> imageModels = new HashSet<>();

        for (MultipartFile file: multipartFiles) {
            ImageModel imageModel = new ImageModel(
                    file.getOriginalFilename(),
                    file.getContentType(),
                    file.getBytes()
            );
            imageModels.add(imageModel);
        }

        return imageModels;
    }
    
    @GetMapping("/allProducts")
//    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Product>> getAllProducts(){
    	List<Product> allProducts = productService.getAllProducts();
    	return new ResponseEntity<List<Product>>(allProducts,HttpStatus.OK);
    }
//    @PreAuthorize("hasRole('admin')")
    @DeleteMapping("/deleteProduct/{productId}")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<String> deleteProductWithId(@PathVariable Integer productId){
    	
    	 String delete = productService.deleteByProductId(productId);
    	 
    	 return new ResponseEntity<String>(delete,HttpStatus.OK);
    	
    }

}
