package com.helpfood.userservice.user.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.helpfood.userservice.listener.DonationTO;
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
        if (user.getTipo() == null || user.getTipo().equals("")) {
            throw new MessageException("USER_ERR004", "Invalid user data (tipo).");
        }

        return userRepository.save(user);
    }

    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        /* buscar doações relacionadas ao usuário
        for (User user : users) {

        }
        */

        return users;
    }

    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    public /*List<DonationTO>*/ void getAllUserDonations(User user) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        queueSender.send(mapper.writeValueAsString(user));
    }

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

}
