package com.backendapis.streamline.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/tenders")
public class TenderApplicationController {

    @PostMapping("/apply")
    public String applyForTender(
            @RequestParam(value = "name", required = true) String name,
            @RequestParam(value = "email", required = true) String email,
            @RequestParam(value = "description", required = true) String description,
            @RequestParam(value = "file", required = true) MultipartFile file) {

        try {
            String uploadDir = "uploads/";
            File uploadFile = new File(uploadDir + file.getOriginalFilename());
            file.transferTo(uploadFile);

            return "Application received from " + name + " with file " + file.getOriginalFilename();
        } catch (IOException e) {
            return "File upload failed!";
        }
    }
}
