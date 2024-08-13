// src/main/java/com/example/logindemo/controller/LoginController.java
package com.ohgiraffers.faq.controller;

import com.ohgiraffers.faq.dto.LoginRequestDTO;
import com.ohgiraffers.faq.dto.LoginResponseDTO;
import com.ohgiraffers.faq.entity.User;
import com.ohgiraffers.faq.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "http://localhost:3000")
public class LoginController {
    @Autowired
    private UserService userService;

    @PostMapping
    public ResponseEntity<LoginResponseDTO> login(@RequestBody LoginRequestDTO loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        return userService.findByUsername(username)
                .filter(user -> user.getPassword().equals(password))
                .map(user -> ResponseEntity.ok(new LoginResponseDTO("로그인 성공")))
                .orElseGet(() -> ResponseEntity.status(401).body(new LoginResponseDTO("유효하지 않은 사용자 이름 또는 비밀번호")));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }
}
