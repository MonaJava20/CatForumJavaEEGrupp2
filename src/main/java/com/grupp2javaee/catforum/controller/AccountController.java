package com.grupp2javaee.catforum.controller;

import com.grupp2javaee.catforum.model.Account;
import com.grupp2javaee.catforum.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepo;



    /*public String getAllAccounts(Model model) {
        model.addAttribute("accounts", accountRepo.findAll());
        return "account.html"; //Eller ev bara "account".
    }*/

    /*@RequestMapping("/getAccounts")
    public String getAccounts(Model model, Account account) {
        model.addAttribute("account", account);
        return "viewAccounts.html";
    }*/

}
