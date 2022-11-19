package com.helpfood.userservice.user.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpfood.userservice.donation.DonationTO;
import com.helpfood.userservice.user.entity.User;
import com.helpfood.userservice.user.service.UserService;
import com.helpfood.userservice.util.exception.MessageException;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/user")
@SecurityScheme(
        name = "Bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody User user) throws MessageException {
        user = userService.save(user);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @GetMapping
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") Integer id) {
        try {
            User user = userService.findById(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("listDonations/{id}")
    public ResponseEntity<List<DonationTO>> getAllUserDonations(@PathVariable("id") Integer id) {
        try {
            User user = userService.findById(id);
            return new ResponseEntity<>(userService.getAllUserDonations(user), HttpStatus.OK) ;
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        userService.delete(id);
    }

}
