package com.controllers;

import com.domain.Manager;
import com.repositories.ManagerRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {


    private ManagerRepository managerRepository;
    private PasswordEncoder passwordEncoder;

    public ManagerController(ManagerRepository managerRepository, PasswordEncoder passwordEncoder) {
        this.managerRepository = managerRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public Manager getManager() {
        Manager manager = new Manager();
        return manager;
    }

    @GetMapping("/manager/add")
    public String addManager(String managerName, String firstName, String lastName, String email, String password) {
        Manager manager = new Manager();
        manager.setManagerName(managerName);
        manager.setFirstName(firstName);
        manager.setLastName(lastName);
        manager.setEmail(email);
        manager.setPassword(passwordEncoder.encode(password));
        managerRepository.save(manager);

        return "manager";
    }
}
