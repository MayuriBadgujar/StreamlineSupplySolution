package com.backendapis.streamline.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tenders")
public class Tender {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    private String title;
    private String category;
    private String deadline;

    @Lob
    private byte[] fileData;

    // Default constructor
    public Tender() {
    }

    // Constructor
    public Tender(String title, String category, String deadline, byte[] fileData) {
        this.title = title;
        this.category = category;
        this.deadline = deadline;
        this.fileData = fileData;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }

    public byte[] getFileData() {
        return fileData;
    }

    public void setFileData(byte[] fileData) {
        this.fileData = fileData;
    }
}
