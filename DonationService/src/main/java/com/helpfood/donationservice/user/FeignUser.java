package com.helpfood.donationservice.user;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@FeignClient("userservice")
public interface FeignUser {

    @GetMapping("user/{id}")
    public ResponseEntity<UserTO> findUserById(@PathVariable("id") Integer userId);

}
