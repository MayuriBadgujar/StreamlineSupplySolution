package com.backendapis.streamline.services; // Correct package

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.backendapis.streamline.entities.Ticket;
import com.backendapis.streamline.repositories.TicketRepository; // Correct import

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public Ticket createTicket(Ticket ticket) {
        ticket.setStatus("Pending");
        return ticketRepository.save(ticket);
    }

    public List<Ticket> getAllTickets() {
        return ticketRepository.findAll();
    }

    public Optional<Ticket> getTicketById(Long id) {
        return ticketRepository.findById(id);
    }

    public Ticket updateTicketResponse(Long id, String response) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setResponse(response);
            ticket.setStatus("Responded");
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public Ticket changeTicketStatus(Long id, String status) {
        return ticketRepository.findById(id).map(ticket -> {
            ticket.setStatus(status);
            return ticketRepository.save(ticket);
        }).orElseThrow(() -> new RuntimeException("Ticket not found"));
    }

    public void deleteTicket(Long id) {
        ticketRepository.deleteById(id);
    }
}
