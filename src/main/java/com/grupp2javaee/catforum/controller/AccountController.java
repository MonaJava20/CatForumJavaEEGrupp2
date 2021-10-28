package com.grupp2javaee.catforum.controller;

import com.grupp2javaee.catforum.model.Account;
import com.grupp2javaee.catforum.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    private CustomUserDetailsService userService;

    //Metoden login returnerar ett objekt av ModelAndView
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    //GET create-formuläret.
    @RequestMapping(value = "/create", method = RequestMethod.GET)
    public ModelAndView signup() {
        ModelAndView modelAndView = new ModelAndView();
        Account account = new Account();
        modelAndView.addObject("account", account);
        modelAndView.setViewName("create");
        return modelAndView;
    }

    //CREATE
    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ModelAndView createNewAccount(Account account, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        Account accountExists = userService.findUserByEmail(account.getEmail());
        if (accountExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Det finns redan ett konto registrerat med den här emailadressen.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("create");
        } else {
            userService.saveAccount(account);
            modelAndView.addObject("successMessage", "Kontot är skapat!");
            modelAndView.addObject("account", new Account());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    @RequestMapping(value = "/forum", method = RequestMethod.GET)
    public ModelAndView forum() {
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Account account = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("currentUser", account);
        modelAndView.addObject("name", "Welcome " + account.getName());
        modelAndView.addObject("userMessage", "Content Available Only for Users with a Role");
        modelAndView.setViewName("forum");
        return modelAndView;
    }

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public ModelAndView home() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("home");
        return modelAndView;
    }

}
