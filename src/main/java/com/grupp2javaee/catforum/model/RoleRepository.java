package com.grupp2javaee.catforum.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface RoleRepository extends MongoRepository<Role, String> {

    Role findByRole(String role);

}
