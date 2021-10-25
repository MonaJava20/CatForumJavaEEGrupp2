package com.grupp2javaee.catforum.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends MongoRepository<Account, String> {

    //Om man vill kan man göra egna metoder här.
    //Annars får man med mycket under huven.

}
