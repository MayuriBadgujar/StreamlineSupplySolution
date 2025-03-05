package com.example.Registation.Entity;

public class CompanyDTO {

    private int companyId;
    private String companyName;
    private String email;
    private String password;

    public CompanyDTO() {
    }

    public CompanyDTO(int companyId, String companyName, String email, String password) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.email = email;
        this.password = password;
    }

    @Override
    public String toString() {
        return "CompanyDTO [companyId=" + companyId + ", companyName=" + companyName + ", email=" + email
                + ", password=" + password + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
                + ", toString()=" + super.toString() + "]";
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
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
