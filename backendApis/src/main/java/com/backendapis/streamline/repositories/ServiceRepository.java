package com.backendapis.streamline.repositories;

import com.backendapis.streamline.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceRepository extends JpaRepository<ServiceEntity, Long> {
    // You can add custom query methods if needed
}
