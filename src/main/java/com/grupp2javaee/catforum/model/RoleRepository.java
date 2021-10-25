package com.grupp2javaee.catforum.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, String> {

    //Om man vill kan man göra egna metoder här.
    //Annars får man med mycket under huven.

    Role findByRole(String role);

}
