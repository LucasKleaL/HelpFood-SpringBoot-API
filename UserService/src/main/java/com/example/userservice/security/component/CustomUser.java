package com.example.userservice.security.component;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

public class CustomUser extends User {

    private static final long serialVersionUID = 1L;
    private Integer guidUser;

    public CustomUser(String email, String password,
                      boolean enabled, boolean accountNonExpired,
                      boolean credentialsNonExpired,
                      boolean accountNonLocked,
                      Collection<? extends GrantedAuthority> authorities,
                      Integer guidUser) {

        super(email == null ? "empty email" : email, password == null ? "not required" : password,
                enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.guidUser = guidUser;
    }

    public Integer getGuidUser() {
        return guidUser;
    }

    public void setGuidUser(Integer guidUser) {
        this.guidUser = guidUser;
    }

}
