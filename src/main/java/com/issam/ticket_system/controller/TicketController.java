package com.issam.ticket_system.controller;

import com.issam.ticket_system.entity.Ticket;
import com.issam.ticket_system.enums.TicketStatus;
import com.issam.ticket_system.service.TicketService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }
    // créer ticket
    @PostMapping
    public Ticket createTicket(@RequestBody Ticket ticket){
        return ticketService.createTicket(ticket);
    }
    // récupérer tous les tickets
    @GetMapping
    public List<Ticket> getAllTickets(){
        return  ticketService.getAllTickets();
    }
    // récupérer ticket par id
    @GetMapping("/{id}")
    public Ticket getTicketById(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }
    // supprimer ticket
    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id){
        return ticketService.deleteTicket(id);
    }
    // changer le status
    @PutMapping("/{id}/status")
    public Ticket changeStatus(@PathVariable Long id, @RequestParam String status) {
        return ticketService.changeStatus(id, TicketStatus.valueOf(status));
    }
}