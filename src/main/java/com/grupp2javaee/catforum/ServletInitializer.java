package com.grupp2javaee.catforum;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

//Denna klass är en spring configurer som hjälper oss att initiera applikationen via en Servlet. Detta för att sedan kunna checka om en användare har rätt att göra en http request
public class ServletInitializer extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(CatforumApplication.class);
    }

}
