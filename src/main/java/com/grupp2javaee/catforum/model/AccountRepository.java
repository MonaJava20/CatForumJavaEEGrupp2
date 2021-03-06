package com.grupp2javaee.catforum.model;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account, String> {

    //Här kan vi skapa några egna metoder
    public List<Account> findByName(String name);
    public Account findByEmail(String email);
    public Account findByUsername(String username);
    public Account deleteByUsername(String username);
    //public Account updateByUsername(String username); //Kan man inte göra
    //public Account updateUsername(String username); //Kan man inte göra

}
