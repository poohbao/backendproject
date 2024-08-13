// src/main/java/com/example/logindemo/service/UserService.java
package com.ohgiraffers.faq.service;

import com.ohgiraffers.faq.entity.User;
import com.ohgiraffers.faq.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findAllUsers() {
        return userRepository.findAll();
    }
}
