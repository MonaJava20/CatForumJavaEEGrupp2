package com.grupp2javaee.catforum.services;

import com.grupp2javaee.catforum.model.Account;
import com.grupp2javaee.catforum.model.AccountRepository;
import com.grupp2javaee.catforum.model.Role;
import com.grupp2javaee.catforum.model.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepo;

    @Autowired
    private RoleRepository roleRepo;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account findUserByEmail(String email) {
        return accountRepo.findByEmail(email);
    }

    public void saveAccount(Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword())); //Denna kod skapar ett krypterat lösenord i databasen
        Role userRole = roleRepo.findByRole("USER");
        account.setRoles(new HashSet<>(Arrays.asList(userRole)));
        accountRepo.save(account);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Account account = accountRepo.findByUsername(username);
        if (account != null) {
            List<GrantedAuthority> authorities = getUserAuthority(account.getRoles());
            return buildUserForAuthentication(account, authorities);
        } else {
            throw new UsernameNotFoundException("username not found");
        }
    }

    private List<GrantedAuthority> getUserAuthority(Set<Role> userRoles) {
        Set<GrantedAuthority> roles = new HashSet<>();
        userRoles.forEach((role) -> {
            roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    private UserDetails buildUserForAuthentication(Account account, List<GrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), authorities);
    }

}
