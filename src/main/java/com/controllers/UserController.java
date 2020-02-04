package com.controllers;

import com.domain.User;
import com.dto.UserDTO;
import com.repositories.UserRepository;
import com.security.UserDetailsImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


@Slf4j
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @RequestMapping("/me")
    public String loggedUserDetails() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }


    @GetMapping
    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public User findUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @PostMapping
    public User registerUser(@ModelAttribute UserDTO dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword()));
        User newUser = new User(dto);

        userRepository.save(newUser);

        return newUser;
    }

    @PutMapping("/{id}")
    public User updateUser(@RequestParam String email, @RequestParam String password, @PathVariable Long id) {

        throw new RuntimeException();
    }
}
