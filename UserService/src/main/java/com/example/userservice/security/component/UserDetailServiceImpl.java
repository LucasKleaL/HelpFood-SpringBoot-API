package com.example.userservice.security.component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;


@Component
public class UserDetailServiceImpl implements UserDetailsService {

    private static final Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDetailServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException, DataAccessException {
        try {
            UserDetails u = new CustomUser("teste@email.com",
                    "$2a$12$LlxKRPHgE2I41V9o2hcN5ud.4dRUA67QCZhDUbh9C6GevJBoyoPo2",
                    true,
                    true,
                    true,
                    true,
                    new ArrayList<>(),
                    null);

            logger.info("Email: " + email + " not found.");

            return u;
        } catch (Exception ex) {
            logger.error("Email: " + email + " not found on DB. Access denied. ");
            throw new UsernameNotFoundException(email);
        }
    }

    private CustomUser getCustomUser(String email) {

        logger.info("getCustomUser: " + email + ".");

        CustomUser customUser = jdbcTemplate.queryForObject(
                "select email, password, guiduser from user where email=?", new Object[] { email },
                new UserRowMapper());

        if (customUser != null) {
            customUser = new CustomUser(customUser.getUsername(), customUser.getPassword(), customUser.isEnabled(),
                    customUser.isAccountNonExpired(), customUser.isCredentialsNonExpired(),
                    customUser.isAccountNonLocked(), getUserRoles(customUser), customUser.getGuidUser());
        }

        return customUser;
    }

    private class UserRowMapper implements RowMapper<CustomUser> {
        @Override
        public CustomUser mapRow(ResultSet rs, int rowNum) throws SQLException {
            return new CustomUser(rs.getString("email"), rs.getString("password"), true, true, true, true,
                    Collections.emptyList(), rs.getInt("guiduser"));
        }
    }

    private List<GrantedAuthority> getUserRoles(CustomUser user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ADMIN"));

        return authorities;
    }

}
