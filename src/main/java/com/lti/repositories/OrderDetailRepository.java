package com.lti.repositories;

import com.lti.entities.OrderDetail;
import com.lti.entities.User;
import org.hibernate.criterion.Order;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderDetailRepository extends CrudRepository<OrderDetail,Integer> {


    List<OrderDetail> findByUser(User user);

    List<OrderDetail> findByOrderStatus(String status);
}
