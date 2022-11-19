package com.helpfood.userservice.user.repository;

import com.helpfood.userservice.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    User findFirstByEmail(String email);

}
