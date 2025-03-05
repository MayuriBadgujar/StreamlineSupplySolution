package com.backendapis.streamline.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.backendapis.streamline.entities.Ticket;
import com.backendapis.streamline.services.TicketService;

@RestController
@RequestMapping("/api/tickets")
@CrossOrigin(origins = "*") // Allow frontend to communicate
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    // 1️⃣ Create a new ticket
    @PostMapping
    public ResponseEntity<Ticket> createTicket(@RequestBody Ticket ticket) {
        return ResponseEntity.ok(ticketService.createTicket(ticket));
    }

    // 2️⃣ Get all tickets
    @GetMapping
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return ResponseEntity.ok(ticketService.getAllTickets());
    }

    // 3️⃣ Get a ticket by ID
    @GetMapping("/{id}")
    public ResponseEntity<Ticket> getTicketById(@PathVariable Long id) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        return ticket.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // 4️⃣ Respond to a ticket
    @PostMapping("/{id}/response")
    public ResponseEntity<Ticket> updateTicketResponse(@PathVariable Long id, @RequestBody Map<String, String> body) {
        if (!body.containsKey("response")) {
            return ResponseEntity.badRequest().build();
        }
        String response = body.get("response");
        return ResponseEntity.ok(ticketService.updateTicketResponse(id, response));
    }

    // 5️⃣ Change ticket status
    @PutMapping("/{id}/status")
    public ResponseEntity<Ticket> changeTicketStatus(@PathVariable Long id, @RequestBody Map<String, String> body) {
        if (!body.containsKey("status")) {
            return ResponseEntity.badRequest().build();
        }
        String status = body.get("status");
        return ResponseEntity.ok(ticketService.changeTicketStatus(id, status));
    }

    // 6️⃣ Close a ticket
    @PutMapping("/{id}/close")
    public ResponseEntity<Ticket> closeTicket(@PathVariable Long id) {
        return ResponseEntity.ok(ticketService.changeTicketStatus(id, "Closed"));
    }

    // 7️⃣ Delete a ticket
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return ResponseEntity.noContent().build();
    }
}
