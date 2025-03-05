package com.backendapis.streamline.services;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.backendapis.streamline.entities.Tender;
import com.backendapis.streamline.repositories.TenderRepository;

@Service
public class TenderService {
    private final TenderRepository tenderRepository;

    public TenderService(TenderRepository tenderRepository) {
        this.tenderRepository = tenderRepository;
    }

    public List<Tender> getAllTenders() {
        return tenderRepository.findAll();
    }

    public Tender addTender(String title, String category, String deadline, MultipartFile file) throws IOException {
        Tender tender = new Tender();
        tender.setTitle(title);
        tender.setCategory(category);
        tender.setDeadline(deadline);
        if (file != null && !file.isEmpty()) {
            tender.setFileData(file.getBytes());
        }
        return tenderRepository.save(tender);
    }

    public void deleteTender(Long id) {
        tenderRepository.deleteById(id);
    }
}
