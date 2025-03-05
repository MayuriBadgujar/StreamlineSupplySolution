package com.example.Registation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Registation.Entity.AdminDTO;
import com.example.Registation.payloads.LoginDTO;
import com.example.Registation.response.LoginMessage;
import com.example.Registation.service.AdminService;

@RestController
@CrossOrigin(origins = "http://localhost:3001")  // Allow requests from localhost:3000
@RequestMapping("api/v1/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @PostMapping(path = "/save")
    public String saveAdmin(@RequestBody AdminDTO adminDTO) {
        String id = adminService.addAdmin(adminDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<LoginMessage> loginAdmin(@RequestBody LoginDTO loginDTO) {
        LoginMessage loginResponse = adminService.loginAdmin(loginDTO);
        
        if (loginResponse.getMessage().equals("Login successful")) {
            return ResponseEntity.ok(loginResponse); // Login successful, send token
        } else {
        	 // If credentials are incorrect, return an error message
            return ResponseEntity.status(401).body(loginResponse);
    }
}
}