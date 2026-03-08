package com.issam.ticket_system.service;

import com.issam.ticket_system.entity.Ticket;
import com.issam.ticket_system.entity.TicketHistory;
import com.issam.ticket_system.repository.TicketHistoryRepository;
import com.issam.ticket_system.repository.TicketRepository;
import org.springframework.stereotype.Service;
import com.issam.ticket_system.enums.TicketStatus;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private TicketHistoryRepository ticketHistoryRepository;

    public TicketService(TicketRepository ticketRepository,
                         TicketHistoryRepository ticketHistoryRepository) {

        this.ticketRepository = ticketRepository;
        this.ticketHistoryRepository = ticketHistoryRepository;
    }
    // créer ticket
    public Ticket createTicket(Ticket ticket){
        ticket.setStatus(TicketStatus.OPEN); // status initial
        ticket.setCreatedAt(LocalDateTime.now()); // date création
        return ticketRepository.save(ticket);
    }
    // récupérer tous les tickets
    public List<Ticket> getAllTickets(){
        return ticketRepository.findAll();
    }
    // récupérer ticket par id
    public Ticket getTicketById (Long id){
        return ticketRepository.findById(id).orElse(null);
    }
    // supprimer ticket
    public  String deleteTicket(Long id){
        if (!ticketRepository.existsById(id)){
            return "ticket not found";
        }
        ticketRepository.deleteById(id);
        return "ticket deleted successfully";
    }
    // changer le status
    public Ticket changeStatus(Long id, TicketStatus newStatus) {

        Ticket ticket = ticketRepository.findById(id).orElse(null);

        if (ticket == null) {
            return null;
        }

        TicketStatus oldStatus = ticket.getStatus();

        ticket.setStatus(newStatus);

        Ticket updatedTicket = ticketRepository.save(ticket);

        TicketHistory history = new TicketHistory(
                "STATUS_CHANGE",
                oldStatus.toString(),
                newStatus.toString(),
                LocalDateTime.now(),
                ticket
        );

        ticketHistoryRepository.save(history);

        return updatedTicket;
    }

}