package com.example.repositories;

import com.example.entities.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role,String> {
}
