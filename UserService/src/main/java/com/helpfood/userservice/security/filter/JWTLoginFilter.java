package com.helpfood.userservice.security.filter;

import com.helpfood.userservice.security.component.CustomUser;
import com.helpfood.userservice.security.component.TokenAuthenticationService;
import com.helpfood.userservice.security.model.Autenticacao;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

    private static final Logger logger = LoggerFactory.getLogger(JWTLoginFilter.class);

    public JWTLoginFilter(String url, AuthenticationManager authManager) {
        super(new AntPathRequestMatcher(url));
        setAuthenticationManager(authManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        String email = request.getParameter("email");
        String password = request.getParameter("password");

        Autenticacao credentials = new Autenticacao();
        credentials.setEmail(email);
        credentials.setPassword(password);

        return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(
                credentials.getEmail(), credentials.getPassword(), Collections.emptyList()));
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response,
                                            FilterChain filterChain, Authentication auth) throws IOException, ServletException {

        CustomUser c = (CustomUser) auth.getPrincipal();

        Autenticacao autenticacao = TokenAuthenticationService.getToken(response, auth.getName(), auth.getAuthorities(), c.getGuidUser());

        ObjectMapper parser = new ObjectMapper();
        String json = parser.writeValueAsString(autenticacao);

        logger.info("User " + autenticacao.getEmail() + " authorized.");

        response.addHeader("Content-Type", "application/json");
        response.getWriter().println(json);
    }
}
