package com.backendapis.streamline.services;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backendapis.streamline.entities.ServiceEntity;
import com.backendapis.streamline.payloads.ServiceDTO;
import com.backendapis.streamline.repositories.ServiceRepository;

@Service
public class ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    private static final String UPLOAD_DIR = "uploads/"; // Change to your preferred upload directory

    // Convert Entity to DTO
    private ServiceDTO convertToDTO(ServiceEntity service) {
        return new ServiceDTO(service.getId(), service.getName(), service.getDescription(), service.getImageUrl());
    }

    // Convert DTO to Entity
    private ServiceEntity convertToEntity(ServiceDTO serviceDTO) {
        return new ServiceEntity(serviceDTO.getName(), serviceDTO.getDescription(), serviceDTO.getImageUrl());
    }

    // Create a new service with image
    public ServiceDTO createService(String name, String description, MultipartFile image) throws IOException {
        String imageUrl = saveImage(image); // Save image and get URL
        ServiceEntity service = new ServiceEntity(name, description, imageUrl);
        service = serviceRepository.save(service);
        return convertToDTO(service);
    }

    // Get all services
    public List<ServiceDTO> getAllServices() {
        return serviceRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Get a service by ID
    public ServiceDTO getServiceById(Long id) {
        Optional<ServiceEntity> service = serviceRepository.findById(id);
        return service.map(this::convertToDTO).orElse(null);
    }

    // Update a service
    public ServiceDTO updateService(Long id, String name, String description, MultipartFile image) throws IOException {
        Optional<ServiceEntity> optionalService = serviceRepository.findById(id);
        if (optionalService.isPresent()) {
            ServiceEntity service = optionalService.get();
            service.setName(name);
            service.setDescription(description);
            
            if (image != null && !image.isEmpty()) {
                service.setImageUrl(saveImage(image));
            }

            return convertToDTO(serviceRepository.save(service));
        }
        return null;
    }

    // Delete a service
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }

    // Save image and return URL
    private String saveImage(MultipartFile image) throws IOException {
        if (image.isEmpty()) return null;

        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String filePath = UPLOAD_DIR + System.currentTimeMillis() + "_" + image.getOriginalFilename();
        File destFile = new File(filePath);
        image.transferTo(destFile);
        return filePath;
    }
}
