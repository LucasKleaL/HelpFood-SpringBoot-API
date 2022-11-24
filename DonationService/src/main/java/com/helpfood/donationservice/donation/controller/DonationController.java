package com.helpfood.donationservice.donation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpfood.donationservice.donation.entity.Donation;
import com.helpfood.donationservice.donation.service.DonationService;
import com.helpfood.donationservice.util.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/donation")
@SecurityScheme(
        name = "Bearer",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class DonationController {
    @Autowired
    DonationService donationService;

    @PostMapping
    public ResponseEntity<?> save(@RequestBody Donation donation) throws MessageException, JsonProcessingException {
        donation = donationService.save(donation);
        return new ResponseEntity<>(donation, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Integer id) {
        donationService.delete(id);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Donation> findById(@PathVariable("id") Integer id) {
        try {
            Donation donation = donationService.findById(id);
            return new ResponseEntity<>(donation, HttpStatus.OK);
        } catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public List<Donation> getAll() {
        return donationService.getAll();
    }

    @GetMapping("/donor/{id}")
    public List<Donation> listByDonorId(@PathVariable("id") Integer userId) {
        return donationService.listByDonorId(userId);
    }

    @GetMapping("/receiver/{id}")
    public List<Donation> listByReceiverId(@PathVariable("id") Integer userId) {
        return donationService.listByReceiverId(userId);
    }

    @PutMapping("/donate/{donationId}")
    public ResponseEntity<?> donate(@PathVariable("donationId") Integer donationId, @RequestBody Donation updatedDonation) throws MessageException {
        try {
            Donation donation = donationService.donate(donationId, updatedDonation);
            return new ResponseEntity<>(donation, HttpStatus.OK);
        }
        catch (NoSuchElementException ex) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
}

