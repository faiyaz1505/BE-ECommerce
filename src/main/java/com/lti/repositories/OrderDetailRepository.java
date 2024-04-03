package com.lti.repositories;

import com.lti.entities.OrderDetail;
import org.springframework.data.repository.CrudRepository;

public interface OrderDetailRepository extends CrudRepository<OrderDetail,Integer> {
}
