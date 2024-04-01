package com.lti.repositories;

import org.springframework.data.repository.CrudRepository;

import com.lti.entities.Role;

public interface RoleRepository extends CrudRepository<Role,String> {
}
