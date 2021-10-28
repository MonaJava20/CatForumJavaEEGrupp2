package com.grupp2javaee.catforum.viewcontroller;

import com.grupp2javaee.catforum.services.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
@ComponentScan("com/grupp2javaee/catforum/viewcontroller") //Detta gör att WebSecurity skannar igenom alla config-classer i mappen
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    //Spring Security behöver någon typ av password kryptering för att ens fungera när det kommer till DBConnection
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //Denna kallar på klassen customizeAuthenticationSuccessHandler
    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = mongoUserDetails();
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(bCryptPasswordEncoder);    //Detta använder samma typ av encoder som användes när kontot skapades. Men denna gång så konverteras lösenordet tillbaks till plain text

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/create").permitAll()
                .antMatchers("/forum/**").hasAuthority("USER")
                .antMatchers("/account/**").hasAuthority("USER")
                //.antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf().disable()
                .formLogin()
                .successHandler(customizeAuthenticationSuccessHandler)
                .loginPage("/login").permitAll()
                .usernameParameter("username") //Detta specificerar vad spring security ska leta efter för variabel
                .passwordParameter("password")
                .and().logout().permitAll()
                .logoutSuccessUrl("/home").and().exceptionHandling();
    }

    //Denna ignorerar security för dessa mappar
    @Override
    public void configure(WebSecurity web) throws Exception {
        web
        .ignoring()
        .antMatchers("/resources/**", "/templates/**", "/static/**", "/css/**", "/js/**", "/image/**","/vendor/**","/fonts/**");
    }

    /*
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

    }*/
}
