package com.helpfood.donationservice.donation.service;

import com.helpfood.donationservice.donation.entity.Donation;
import com.helpfood.donationservice.donation.repository.DonationRepository;
import com.helpfood.donationservice.producer.QueueSender;
import com.helpfood.donationservice.product.ProductTO;
import com.helpfood.donationservice.user.FeignUser;
import com.helpfood.donationservice.util.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class DonationService {

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private FeignUser feignUser;

    public Donation save(Donation donation) throws MessageException {
        // Title validation
        if (donation.getTitle() == null || donation.getTitle().equals("")) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (Title cannot be null).");
        }
        // DonorId validation
        if (donation.getDonorId() == null || donation.getDonorId() == 0) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (DonorId cannot be null).");
        }
        if (feignUser.findUserById(donation.getDonorId()).getStatusCodeValue() == 404) {
            throw new MessageException("DONATION_ERR002", "The user with id "+donation.getDonorId()+" don't exist.");
        }
        // DonorAddress validation
        if (donation.getDonorAddress() == null || donation.getDonorAddress().equals("")) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (DonorAddress cannot be null).");
        }
        // ReceiverId validation
        if (donation.getReceiverId() != null || !donation.getReceiverId().equals("")) {
            if (feignUser.findUserById(donation.getReceiverId()).getStatusCodeValue() == 404) {
                throw new MessageException("DONATION_ERR002", "The user with id "+donation.getDonorId()+" don't exist.");
            }
        }
        // DonationProductsIds validation
        if (donation.getProductsIds() == null || donation.getProductsIds().isEmpty()) {
            throw new MessageException("DONATION_ERR001", "Invalid donation data (ProductsIds cannot be null).");
        }
        for (Integer productId : donation.getProductsIds()) {

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