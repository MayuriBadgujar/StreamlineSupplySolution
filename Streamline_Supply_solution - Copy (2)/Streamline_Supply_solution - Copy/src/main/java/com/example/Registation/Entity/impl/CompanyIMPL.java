package com.example.Registation.Entity.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Registation.Entity.Company;
import com.example.Registation.Entity.CompanyDTO;
import com.example.Registation.payloads.LoginDTO;
import com.example.Registation.repositories.CompanyRepo;
import com.example.Registation.response.LoginMessage;  // Assuming LoginMessage is your custom class
import com.example.Registation.service.CompanyService;

@Service
public class CompanyIMPL implements CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public String addCompany(CompanyDTO companyDTO) {
        // Creating a Company object from the DTO
        Company company = new Company(
                companyDTO.getCompanyId(),
                companyDTO.getCompanyName(),
                companyDTO.getEmail(),
                passwordEncoder.encode(companyDTO.getPassword())  // Encrypting the password
        );

        // Saving the company to the repository
        companyRepo.save(company);

        return "Company " + company.getCompanyName() + " added successfully.";
    }

    @Override
    public LoginMessage loginCompany(LoginDTO loginDTO) {
        // Searching for the company by email
        Company company = companyRepo.findByEmail(loginDTO.getEmail());

        if (company != null) {
            // Compare the provided password with the stored (encoded) password
            boolean isPwdRight = passwordEncoder.matches(loginDTO.getPassword(), company.getPassword());

            if (isPwdRight) {
                // Successful login response
                return new LoginMessage("Login successful", true);
            } else {
                // Incorrect password response
                return new LoginMessage("Incorrect password", false);
            }
        } else {
            // Company with the given email doesn't exist
            return new LoginMessage("Email not found", false);
        }
    }
}
