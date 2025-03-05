package com.service.entity;



import jakarta.persistence.*;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(length = 500)
    private String description;

    @Column(nullable = false)
    private double price;

    @Column(name = "make_company", nullable = false)
    private String makeCompany;

    @Column(name = "image_url")
    private String image;

    @Column(name = "pdf_url")
    private String pdf;
    
    @ManyToOne
    @JoinColumn(name = "category_id") // Foreign key in Booking table
    private Category category;


    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    private String createdBy;

    public Booking() {
    }

    public Booking(Long id, String name, String description, double price, String makeCompany, String image, String pdf, Date createdDate, String createdBy) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.makeCompany = makeCompany;
        this.image = image;
        this.pdf = pdf;
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getMakeCompany() {
        return makeCompany;
    }

    public void setMakeCompany(String makeCompany) {
        this.makeCompany = makeCompany;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPdf() {
        return pdf;
    }

    public void setPdf(String pdf) {
        this.pdf = pdf;
    }
    
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }


    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", name=" + name + ", description=" + description + ", price=" + price
                + ", makeCompany=" + makeCompany + ", image=" + image + ", pdf=" + pdf + ", createdDate=" + createdDate
                + ", createdBy=" + createdBy + "]";
    }

	
}
