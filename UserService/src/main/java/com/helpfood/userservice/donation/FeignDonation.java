package com.helpfood.userservice.donation;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.NoSuchElementException;

@Controller
@FeignClient("donationservice")
public interface FeignDonation {

    @GetMapping("donation/donor/{id}")
    public List<DonationTO> findByDonorId(@PathVariable("id") Integer donorId);

    @GetMapping("donation/receiver/{id}")
    public List<DonationTO> findByReceiverId(@PathVariable("id") Integer receiverId);
}
