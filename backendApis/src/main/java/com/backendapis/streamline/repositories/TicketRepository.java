package com.backendapis.streamline.repositories; // Use the correct package

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.backendapis.streamline.entities.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
}
