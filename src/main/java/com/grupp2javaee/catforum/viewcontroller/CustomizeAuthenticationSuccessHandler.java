package com.grupp2javaee.catforum.viewcontroller;

import java.io.IOException;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    public CustomizeAuthenticationSuccessHandler() {
    }

    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setStatus(200);
        Iterator var4 = authentication.getAuthorities().iterator();

        while(var4.hasNext()) {
            GrantedAuthority auth = (GrantedAuthority)var4.next();
            if ("USER".equals(auth.getAuthority())) {
                response.sendRedirect("/home");
            }
        }

    }
}