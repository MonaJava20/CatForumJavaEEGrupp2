package com.grupp2javaee.catforum.controller;

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

/*
@Service
public class CustomUserDetailService implements UserDetailsService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public Account findUserByEmail(String email){
        return accountRepository.findByEmail(email);
    }

    public void saveAccount (Account account) {
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        Role accountRole = roleRepository.findByRole("ADMIN");
        account.setRoles(new HashSet<>(Arrays.asList(accountRole)));
        accountRepository.save(account);
        System.out.println("New account successfully created!");
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Account account = accountRepository.findByEmail(email);

        if (account != null){
            List<GrantedAuthority> authorities = getUserAuthority(account.getRoles());
            return buildUserForAuthentication(account, authorities);
        } else {
            throw new UsernameNotFoundException("Email not found!");
        }

        private List<GrantedAuthority> getUserAuthority(Set<Role> accountRoles) {
            Set<GrantedAuthority> roles = new HashSet<>();
            accountRoles.forEach((role) -> {
                roles.add(new SimpleGrantedAuthority(role.getRole()));
        });

            List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
            return grantedAuthorities;
    }
        private userDetails buildUserForAuthentication(Account account, List<GrantedAuthority> authorities) {
            return new org.springframework.security.core.userdetails.User(account.getEmail(), account.getPassword(), authorities);
        }
}
*/