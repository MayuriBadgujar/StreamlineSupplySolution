package com.example.Registation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "company")
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "companyid")  // No need for length for integers
    private int companyid;

    @Column(name = "companyname", length = 255)
    private String companyname;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "password", length = 255)
    private String password;

    // Default constructor
    public Company() {}

    // Constructor with fields
    public Company(int companyid, String companyname, String email, String password) {
        this.companyid = companyid;
        this.companyname = companyname;
        this.email = email;
        this.password = password;
    }

    // Getters and Setters
    public int getCompanyId() {
        return companyid;
    }

    public void setCompanyId(int companyid) {
        this.companyid = companyid;
    }

    public String getCompanyName() {
        return companyname;
    }

    public void setCompanyName(String companyname) {
        this.companyname = companyname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
