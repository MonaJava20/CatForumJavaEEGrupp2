package com.grupp2javaee.catforum.viewcontroller;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/images/**").permitAll()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/create").permitAll() //CREATE
                .antMatchers("/mypage").hasRole("USER") //GET
                .antMatchers("/update").hasRole("USER") //UPDATE
                .antMatchers("/delete").hasRole("USER") //DELETE
                .antMatchers("/delete/{id}").hasRole("ADMIN")
                .antMatchers("/forum").hasRole("USER")
                .antMatchers("/account").hasRole("USER")
                //.antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/home");
    }

    //Denna ignorerar security för dessa mappar
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        .ignoring()
        .antMatchers("/resources/**", "/templates/**", "/static/**", "/css/**", "/js/**", "/image/**","/vendor/**","/fonts/**");
    }

    //Debug user :)
    @Bean
    @Override
    public UserDetailsService userDetailsService() {

        //Detta är ett hårdkodat test-konto.
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("user")
                        .password("password")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);

        /*UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("USER","ADMIN")
                        .build();
        return new InMemoryUserDetailsManager(admin);*/
    }
}
