package com.issam.ticket_system.controller;

import com.issam.ticket_system.dto.TicketCreateDTO;
import com.issam.ticket_system.dto.TicketResponseDTO;
import com.issam.ticket_system.enums.TicketStatus;
import com.issam.ticket_system.service.TicketService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tickets")
public class TicketController {

    private TicketService ticketService;

    public TicketController(TicketService ticketService){
        this.ticketService = ticketService;
    }

    // CREATE
    @PostMapping
    public TicketResponseDTO createTicket(@RequestBody TicketCreateDTO dto){
        return ticketService.createTicket(dto);
    }

    // GET ALL
    @GetMapping
    public List<TicketResponseDTO> getAllTickets(){
        return ticketService.getAllTickets();
    }

    // GET BY ID
    @GetMapping("/{id}")
    public TicketResponseDTO getTicketById(@PathVariable Long id){
        return ticketService.getTicketById(id);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteTicket(@PathVariable Long id){
        return ticketService.deleteTicket(id);
    }

    // CHANGE STATUS
    @PutMapping("/{id}/status")
    public TicketResponseDTO changeStatus(@PathVariable Long id,
                                          @RequestParam String status){

        return ticketService.changeStatus(id, TicketStatus.valueOf(status));
    }
}