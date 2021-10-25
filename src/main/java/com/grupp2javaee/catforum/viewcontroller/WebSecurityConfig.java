package com.grupp2javaee.catforum.viewcontroller;

import com.grupp2javaee.catforum.controller.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan({"com.grupp2javaee.catforum.viewcontroller"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    CustomizeAuthenticationSuccessHandler customizeAuthenticationSuccessHandler;

    @Bean
    public UserDetailsService mongoUserDetails() {
        return new CustomUserDetailsService();
    }

    //Detta testar vi att cofiguera authentication och använder
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        UserDetailsService userDetailsService = this.mongoUserDetails();
        auth.userDetailsService(userDetailsService).passwordEncoder(this.bCryptPasswordEncoder);
    }

    //.antMatchers(new String[]{"/"})).permitAll()
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/", "/home").permitAll()
                .antMatchers("/create").permitAll()
                .antMatchers("/forum").hasRole("USER")
                .antMatchers("/account").hasRole("USER")
                //.antMatchers("/admin").hasRole("ADMIN")
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .successHandler(this.customizeAuthenticationSuccessHandler)//Denna kanske ska bort?
                .loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .logoutSuccessUrl("/home");
    }

    /*
    public NewMethod newMethod () {
        http.authorizeRequests()
                .antMatchers(new String[]{"/"}).permitAll()
                .antMatchers(new String[]{"/login"}).permitAll()
                .antMatchers(new String[]{"/signup"}).permitAll()
                .antMatchers(new String[]{"/dashboard/**"}).hasAuthority("ADMIN")
                .anyRequest().authenticated()
                .and()
                .csrf()
                .disable()
                .formLogin()
                .successHandler(this.customizeAuthenticationSuccessHandler)
                .loginPage("/login")
                .failureUrl("/login?error=true")
                .usernameParameter("email")
                .passwordParameter("password")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")
                .and()
                .exceptionHandling();
    }
     */

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

        /*UserDetails admin =
                User.withDefaultPasswordEncoder()
                        .username("admin")
                        .password("password")
                        .roles("USER","ADMIN")
                        .build();*/

        return new InMemoryUserDetailsManager(user);

    }
}
