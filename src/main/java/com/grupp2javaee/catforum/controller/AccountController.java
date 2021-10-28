package com.grupp2javaee.catforum.controller;

import com.grupp2javaee.catforum.model.Account;
import com.grupp2javaee.catforum.model.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.aggregation.ArithmeticOperators;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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

    @GetMapping("/login")
    public String showLoginPage() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
            return "/login";
        }
        return "redirect:/";
    }

    /*
    @RequestMapping(value = "/login", method = RequestMethod.POST)
        public Account<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));SecurityContextHolder.getContext().setAuthentication(authentication);String jwt = jwtUtils.generateJwtToken(authentication);UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();List<String> roles = userDetails.getAuthorities().stream().map(item -> item.getAuthority()).collect(Collectors.toList());return ResponseEntity.ok(new JwtResponse(jwt,
            userDetails.getId(),
            userDetails.getUsername(),
            userDetails.getEmail(),
            roles));
    }*/

    //GET mina uppgifter/account  tilll mypage.



    public Account getAccountByUserName(String userName){
        return accountRepo.findByUsername(userName);
    }

    /*public void deleteAccountByUserName(String name){
         accountRepo.deleteByName(name);
    }*/

    /*@RequestMapping(value="/delete/{name}", method=RequestMethod.DELETE)
    public void deleteAccount(@PathVariable String name) {
        accountRepo.deleteByName("Katarina");
    }*/

    //GET UPDATE-vy
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public ModelAndView update() {
        ModelAndView modelAndView = new ModelAndView();
        Account account = new Account(); //Behövs denna och rad nedan?
        modelAndView.addObject("account", account);
        modelAndView.setViewName("update");
        return updateAccount();
        //return modelAndView;
    }

    //UPDATE
    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ModelAndView updateAccount() {
        ModelAndView modelAndView = new ModelAndView();
        Account account = accountRepo.findByUsername("KattJulle");
        System.out.println("Har vi hittat KattJulle: " + account);
        account.setUsername("KattDrulle");
        //Spara i databas accountRepo
        accountRepo.save(account);
        //accountRepo.updateUsername("KattJulle");
        //account.setUsername("KattDrulle");
        modelAndView.setViewName("update");
        System.out.println("Kolla i databasen så att namnet är uppdaterat");
        return modelAndView;
    }

    //GET DELETE-vy
    @RequestMapping(value = "/delete", method = RequestMethod.GET)
    public ModelAndView delete() {
        ModelAndView modelAndView = new ModelAndView();
        Account account = new Account();
        modelAndView.addObject("account", account);
        modelAndView.setViewName("delete");
        return deleteAccount(); //deleteAccount() = ett objekt av ModelView
    }

    //DELETE
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ModelAndView deleteAccount() {
        ModelAndView modelAndView = new ModelAndView();
        //accountRepo.deleteAll();
        //deleteAccountByUserName("Katarina");
        //modelAndView.("account", account);
        accountRepo.deleteByUsername("Kattis");
        modelAndView.setViewName("delete");
        System.out.println("Kolla i databasen så att den är borta");
        return modelAndView;
    }

    //Se ett konto i konsollen
    //GET
    @RequestMapping(value = "/mypage", method = RequestMethod.GET)
    public ModelAndView viewAccount() {
        ModelAndView modelAndView = new ModelAndView();
        Account account = getAccountByUserName("Kattis");
        //modelAndView.("account", account);
        modelAndView.setViewName("mypage");
        System.out.println("Mitt konto: ");
        System.out.println(account);
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
