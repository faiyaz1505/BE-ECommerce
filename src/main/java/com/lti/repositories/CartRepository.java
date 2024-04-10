package com.lti.repositories;

import com.lti.entities.Cart;
import com.lti.entities.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends CrudRepository<Cart,Integer> {

    public List<Cart> findByUser(User user);

}
