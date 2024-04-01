package com.lti.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lti.entities.User;

public interface UserRepository extends CrudRepository<User,String> {
}
