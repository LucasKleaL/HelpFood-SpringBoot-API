package com.helpfood.donationservice.donation.service;

import com.helpfood.donationservice.donation.entity.Donation;
import com.helpfood.donationservice.donation.repository.DonationRepository;
import com.helpfood.donationservice.producer.QueueSender;
import com.helpfood.donationservice.user.FeignUser;
import com.helpfood.donationservice.user.UserTO;
import com.helpfood.donationservice.util.exception.MessageException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private FeignUser feignUser;

    public Donation save(Donation donation) throws MessageException, FeignException {
        // Title validation
        if (donation.getTitle() == null || donation.getTitle().equals("")) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (Title cannot be null).");
        }
        // DonorId validation
        if (donation.getDonorId() == null || donation.getDonorId() == 0) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (DonorId cannot be null).");
        }
        if (feignUser.findUserById(donation.getDonorId()) == null) {

            throw new MessageException("DONATION_ERR002", "The user with id "+donation.getDonorId()+" don't exist.");
        }

        // CreationDate assign
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        donation.setCreationDate(formatter.format(date));

        return donationRepository.save(donation);
    }

    public Donation findById(Integer id) {
        return donationRepository.findById(id).get();
    }

    public void delete(int id) {
        donationRepository.deleteById(id);
    }

    public List<Donation> getAll() {
        return donationRepository.findAll();
    }

    public List<Donation> listByDonorId(Integer donorId) {
        return donationRepository.findAllByDonorId(donorId);
    }

    public List<Donation> listByReceiverId(Integer receiverId) {
        return donationRepository.findAllByReceiverId(receiverId);
    }

}