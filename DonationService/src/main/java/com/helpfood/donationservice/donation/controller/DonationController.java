package com.helpfood.donationservice.donation.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.helpfood.donationservice.donation.entity.Donation;
import com.helpfood.donationservice.donation.service.DonationService;
import com.helpfood.donationservice.util.exception.MessageException;
import com.helpfood.donationservice.util.pdf.PdfGenerator;
import com.lowagie.text.DocumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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

    @GetMapping("/report")
    public void reportAll(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";

        response.setHeader(headerkey, headervalue);

        List<Donation> donations = donationService.getAll();

        PdfGenerator generator = new PdfGenerator();
        generator.generate(donations, response);
    }

    @GetMapping("/reportByDonor/{id}")
    public void reportByDonorId(@PathVariable("id") Integer donorId, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";

        response.setHeader(headerkey, headervalue);

        List<Donation> donations = donationService.listByDonorId(donorId);

        PdfGenerator generator = new PdfGenerator();
        generator.generate(donations, response);
    }

    @GetMapping("/reportByReceiver/{id}")
    public void generatePdfFile(@PathVariable("id") Integer receiverId, HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");

        DateFormat dateFormat = new SimpleDateFormat("YYYY-MM-DD:HH:MM:SS");
        String currentDateTime = dateFormat.format(new Date());
        String headerkey = "Content-Disposition";
        String headervalue = "attachment; filename=student" + currentDateTime + ".pdf";

        response.setHeader(headerkey, headervalue);

        List<Donation> donations = donationService.listByReceiverId(receiverId);

        PdfGenerator generator = new PdfGenerator();
        generator.generate(donations, response);
    }
}

