package com.example.userservice.security.component;

import com.example.userservice.security.model.Autenticacao;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class TokenAuthenticationService {

    // EXPIRATION_TIME = 100 anos
    static final long EXPIRATION_TIME = (100 * 365 * 24 * 60 * 60 * 1000);
    static final String SECRET = "HelpFood";
    static final String TOKEN_PREFIX = "Bearer";
    static final String HEADER_STRING = "Authorization";

    public static Autenticacao getToken(HttpServletResponse response, String email,
                                        Collection<? extends  GrantedAuthority> authorities, Integer guidUser) {
        String token = Jwts.builder()
                .setSubject(email)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET).compact();

        Autenticacao autenticacao = new Autenticacao();
        autenticacao.setToken(token);
        autenticacao.setEmail(email);

        return autenticacao;
    }

    @SuppressWarnings("unchecked")
    public static Authentication getAuthentication(HttpServletRequest request) {
        String token = request.getHeader(HEADER_STRING);

        if (token != null) {
            // faz parse do token
            Claims claims = null;

            try {
                claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            } catch (ExpiredJwtException e) {
                claims = e.getClaims();
            }

            if (claims != null) {

                if (claims.getSubject() != null) {
                    return new UsernamePasswordAuthenticationToken(claims.getSubject(),new ArrayList<>(), new ArrayList<>());
                }

            }
        }
        return null;
    }

    public static Map<String, String> getValuesFromToken(HttpServletRequest request) {

        String token = request.getHeader(HEADER_STRING);

        if (token != null) {

            Claims claims = null;

            try {
                claims = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token.replace(TOKEN_PREFIX, "")).getBody();
            } catch (ExpiredJwtException e) {
                claims = e.getClaims();
            }

            if (claims != null) {

                Map<String, String> values = new HashMap<>();

                Integer guidUser = (Integer) claims.get("GUIDUSER");
                values.put("guidUser", guidUser == null ? null : guidUser.toString());

                return values;
            }

        }

        return null;
    }

}
