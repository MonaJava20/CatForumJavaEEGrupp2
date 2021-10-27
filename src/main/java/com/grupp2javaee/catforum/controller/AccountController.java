package com.grupp2javaee.catforum.controller;

import com.grupp2javaee.catforum.model.Account;
import com.grupp2javaee.catforum.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccountController {

    @Autowired
    AccountRepository accountRepo;

    //Metoden login returnerar ett objekt av ModelAndView
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
        public Account<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));SecurityContextHolder.getContext().setAuthentication(authentication);String jwt = jwtUtils.generateJwtToken(authentication);UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
    }*/



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
        Account accountExists = accountRepo.findByEmail(account.getEmail());
        if (accountExists != null) {
            bindingResult
                    .rejectValue("email", "error.user",
                            "Det finns redan ett konto registrerat med den här emailadressen.");
        }
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("create");
        } else {
            accountRepo.save(account);
            modelAndView.addObject("successMessage", "Kontot är skapat!");
            modelAndView.addObject("account", new Account());
            modelAndView.setViewName("login");
        }
        return modelAndView;
    }

    //UPDATE


    //@RequestMapping(value="/account", method = RequestMethod.GET)



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
