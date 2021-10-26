/*package com.grupp2javaee.catforum.services;

import com.grupp2javaee.catforum.model.Account;
import com.grupp2javaee.catforum.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;

public class CustomUserDetailsService {

    @Autowired
    private AccountRepository accountRepo;

    public void saveAccount(Account account) {
        account.setPassword(account.getPassword());
        accountRepo.save(account);
    }
}*/
