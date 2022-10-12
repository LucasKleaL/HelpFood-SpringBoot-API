package com.example.userservice.user.service;

import com.example.userservice.user.entity.User;
import com.example.userservice.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) { //exeçao
        if (user.getName() == null || user.getName().equals("") || user.getName().length() > 55) {
            //throw exception
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

    public void delete(Integer id) {
        userRepository.deleteById(id);
    }

}
