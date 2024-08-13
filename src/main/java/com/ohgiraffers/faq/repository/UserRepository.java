// src/main/java/com/example/logindemo/repository/UserRepository.java
package com.ohgiraffers.faq.repository;

import com.ohgiraffers.faq.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
