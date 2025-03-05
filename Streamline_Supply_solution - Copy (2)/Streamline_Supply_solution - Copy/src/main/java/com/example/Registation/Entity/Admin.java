package com.example.Registation.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "admin")  // Ensure that the table name is 'admin'
public class Admin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "adminid")
    private int adminid;

    @Column(name = "adminname")
    private String adminname;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    public Admin() {
    }

    public Admin(int adminid, String adminname, String email, String password) {
        this.adminid = adminid;
        this.adminname = adminname;
        this.email = email;
        this.password = password;
    }

    public int getAdminid() {
        return adminid;
    }

    public void setAdminid(int adminid) {
        this.adminid = adminid;
    }

    public String getAdminname() {
        return adminname;
    }

    public void setAdminname(String adminname) {
        this.adminname = adminname;
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
