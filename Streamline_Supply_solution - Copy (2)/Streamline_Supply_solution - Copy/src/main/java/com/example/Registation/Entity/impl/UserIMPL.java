package com.example.Registation.Entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Registation.Entity.User;
import com.example.Registation.Entity.UserDTO;
import com.example.Registation.payloads.LoginDTO;
import com.example.Registation.repositories.UserRepo;
import com.example.Registation.response.LoginMessage;  // Assuming LoginMessage is your custom class
import com.example.Registation.service.UserService;

@Service
public class UserIMPL implements UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addUser(UserDTO userDTO) {
        // Creating an Employee object from the DTO
        User user = new User(
                userDTO.getUserid(),
                userDTO.getUsername(),
                userDTO.getEmail(),
                passwordEncoder.encode(userDTO.getPassword())  // Encrypting the password
        );

        // Saving the user to the repository
        userRepo.save(user);

        return "User " + user.getUsername() + " added successfully.";
    }

    @Override
    public LoginMessage loginUser(LoginDTO loginDTO) {
        // Searching for the user by email
        User user = userRepo.findByEmail(loginDTO.getEmail());

        if (user != null) {
            // Compare the provided password with the stored (encoded) password
            boolean isPwdRight = passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());

            if (isPwdRight) {
                // Successful login response
                return new LoginMessage("Login successful", true);
            } else {
                // Incorrect password response
                return new LoginMessage("Incorrect password", false);
            }
        } else {
            // Employee with the given email doesn't exist
            return new LoginMessage("Email not found", false);
        }
    }
}
