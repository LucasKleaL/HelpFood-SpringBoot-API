package com.helpfood.userservice.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpfood.userservice.donation.DonationTO;
import com.helpfood.userservice.donation.FeignDonation;
import com.helpfood.userservice.producer.QueueSender;
import com.helpfood.userservice.user.entity.User;
import com.helpfood.userservice.user.repository.UserRepository;
import com.helpfood.userservice.util.exception.MessageException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private QueueSender queueSender;

    @Autowired
    private FeignDonation feignDonation;

    public User save(User user) throws MessageException {
        if (user.getName() == null || user.getName().equals("") || user.getName().length() > 55) {
            throw new MessageException("USER_ERR001", "Invalid user data (name).");
        }
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new MessageException("USER_ERR002", "Invalid user data (email).");
        }
        if (user.getCnpj() == null || user.getCnpj().equals("")) {
            throw new MessageException("USER_ERR003", "Invalid user data (cnpj)");
        }
        if (user.getRole() == null || user.getRole().equals("")) {
            throw new MessageException("USER_ERR004", "Invalid user data (role).");
        }

        return userRepository.save(user);
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            List<DonationTO> userDonations;
            if (user.getRole().equals("empresa")) {
                userDonations = feignDonation.findByDonorId(user.getId());
            }
            else {
                userDonations = feignDonation.findByReceiverId(user.getId());
            }
            user.setDonations(userDonations);
        }

        return users;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    public List<DonationTO> getAllUserDonations(User user){
        return feignDonation.findByDonorId(user.getId());
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

    public void addDonationId(DonationTO donation) {
        User user;
        if (donation.getReceiverId() == null) {
            user = userRepository.findById(donation.getDonorId()).get();
        }
        else {
            user = userRepository.findById(donation.getReceiverId()).get();
        }
        user.addDonationId(donation.getId());
    }

    public void removeDonationId(DonationTO donation) {
        User user;
        if (donation.getReceiverId() == null) {
            user = userRepository.findById(donation.getDonorId()).get();
        }
        else {
            user = userRepository.findById(donation.getReceiverId()).get();
        }
        user.removeDonationId(donation.getId());
    }

}
