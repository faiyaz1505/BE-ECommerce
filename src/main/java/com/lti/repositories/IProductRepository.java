package com.lti.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lti.entities.Product;

@Repository
public interface IProductRepository  extends JpaRepository<Product, Integer>{

}
