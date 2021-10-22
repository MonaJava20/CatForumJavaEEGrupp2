package com.grupp2javaee.catforum.viewcontroller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    //Adderar Controllers som styr våra Views.
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/account").setViewName("account");
        registry.addViewController("/forum").setViewName("forum");
        registry.addViewController("/create").setViewName("create");

        //registry.addViewController("/admin").setViewName("admin"); //Om tid finns

    }

}