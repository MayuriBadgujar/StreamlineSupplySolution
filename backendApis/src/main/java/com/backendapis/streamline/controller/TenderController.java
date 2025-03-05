package com.backendapis.streamline.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.backendapis.streamline.entities.Tender;
import com.backendapis.streamline.services.TenderService;

@RestController
@RequestMapping("/api/tenders")
@CrossOrigin("*") // Allow frontend access
public class TenderController {
    private final TenderService tenderService;

    public TenderController(TenderService tenderService) {
        this.tenderService = tenderService;
    }

    @GetMapping
    public List<Tender> getAllTenders() {
        return tenderService.getAllTenders();
    }

    @PostMapping
    public ResponseEntity<Tender> addTender(
        @RequestParam String title,
        @RequestParam String category,
        @RequestParam String deadline,
        @RequestParam(required = false) MultipartFile file) {
        try {
            Tender newTender = tenderService.addTender(title, category, deadline, file);
            return ResponseEntity.ok(newTender);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTender(@PathVariable Long id) {
        tenderService.deleteTender(id);
        return ResponseEntity.noContent().build();
    }
}
