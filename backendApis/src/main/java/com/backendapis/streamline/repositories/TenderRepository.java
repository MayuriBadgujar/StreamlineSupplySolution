package com.backendapis.streamline.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backendapis.streamline.entities.Tender;

public interface TenderRepository extends JpaRepository<Tender, Long> {
}
