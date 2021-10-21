package com.grupp2javaee.catforum.model;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepository extends CrudRepository<Account, Long> {

    //Om man vill kan man göra egna metoder här.
    //Annars får man med mycket under huven.

}
