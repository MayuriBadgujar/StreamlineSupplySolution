package com.example.Registation.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Registation.Entity.CompanyDTO;
import com.example.Registation.payloads.LoginDTO;
import com.example.Registation.response.LoginMessage;
import com.example.Registation.service.CompanyService;

@RestController
@CrossOrigin(origins = "http://localhost:3000")  // Allow requests from localhost:3000
@RequestMapping("api/v1/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping(path = "/save")
    public String saveCompany(@RequestBody CompanyDTO companyDTO) {
        String id = companyService.addCompany(companyDTO);
        return id;
    }

    @PostMapping(path = "/login")
    public ResponseEntity<?> loginCompany(@RequestBody LoginDTO loginDTO) {
        // Call the service method to validate login and return a response
        LoginMessage loginResponse = companyService.loginCompany(loginDTO);

        // Return response based on login success or failure
        if (loginResponse.getMessage().equals("Login successful")) {
            return ResponseEntity.ok(loginResponse); // Login successful, send token
        } else {
            // If credentials are incorrect, return an error message
            return ResponseEntity.status(401).body(loginResponse);
        }
    }
}
