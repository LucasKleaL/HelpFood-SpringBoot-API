package com.helpfood.userservice.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpfood.userservice.donation.DonationTO;
import com.helpfood.userservice.donation.FeignDonation;
import com.helpfood.userservice.producer.QueueSender;
import com.helpfood.userservice.user.entity.User;
import com.helpfood.userservice.user.repository.UserRepository;
import com.helpfood.userservice.util.exception.MessageException;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
        // Name validation
        if (user.getName() == null || user.getName().equals("")) {
            throw new MessageException("USER_ERR001", "Invalid user data (Name cannot be null).");
        }
        if (user.getName().length() > 55 || user.getName().length() < 3) {
            throw new MessageException("USER_ERR001", "Invalid user data (Name must be bigger than 2 and smaller than 55 char).");
        }
        // Email validation
        if (user.getEmail() == null || user.getEmail().equals("")) {
            throw new MessageException("USER_ERR001", "Invalid user data (Email cannot be null).");
        }
        if (userRepository.findFirstByEmail(user.getEmail()) != null) {
            throw new MessageException("USER_ERR002", "Email already registered.");
        }
        // Cnpj validation
        if (user.getCnpj() == null || user.getCnpj().equals("")) {
            throw new MessageException("USER_ERR001", "Invalid user data (CNPJ cannot be null)");
        }
        if (user.getCnpj().length() != 14) {
            throw new MessageException("USER_ERR001", "Invalid user data (CNPJ must be 14 numbers).");
        }
        // Role validation
        if (user.getRole() == null || user.getRole().equals("")) {
            throw new MessageException("USER_ERR001", "Invalid user data (Role cannot be null).");
        }
        // Email verified attribution
        if (user.getEmailVerified() == null) {
            user.setEmailVerified(false);
        }
        // Allowed attribution
        if (user.getAllowed() == null) {
            user.setAllowed(true);
        }
        // CountDonations assign
        user.setCountDonations(0);

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
        User user = userRepository.findById(id).get();
        List<DonationTO> userDonations;
        if (user.getRole().equals("empresa")) {
            userDonations = feignDonation.findByDonorId(user.getId());
        }
        else {
            userDonations = feignDonation.findByReceiverId(user.getId());
        }
        user.setDonations(userDonations);
        return user;
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
        user.addDonation();
        userRepository.save(user);
    }

    public void removeDonationId(DonationTO donation) {
        User user;
        if (donation.getReceiverId() == null) {
            user = userRepository.findById(donation.getDonorId()).get();
        }
        else {
            user = userRepository.findById(donation.getReceiverId()).get();
        }
        user.removeDonation();
        userRepository.save(user);
    }

}
