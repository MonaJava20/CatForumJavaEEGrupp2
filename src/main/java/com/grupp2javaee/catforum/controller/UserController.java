package com.grupp2javaee.catforum.controller;

import com.grupp2javaee.catforum.model.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepo;



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
