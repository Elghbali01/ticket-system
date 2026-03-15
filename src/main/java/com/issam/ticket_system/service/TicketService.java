package com.issam.ticket_system.service;

import com.issam.ticket_system.dto.TicketCreateDTO;
import com.issam.ticket_system.dto.TicketResponseDTO;
import com.issam.ticket_system.entity.Ticket;
import com.issam.ticket_system.entity.TicketHistory;
import com.issam.ticket_system.entity.User;
import com.issam.ticket_system.enums.TicketStatus;
import com.issam.ticket_system.mapper.TicketMapper;
import com.issam.ticket_system.repository.TicketHistoryRepository;
import com.issam.ticket_system.repository.TicketRepository;
import com.issam.ticket_system.repository.UserRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TicketService {

    private TicketRepository ticketRepository;
    private TicketHistoryRepository ticketHistoryRepository;
    private UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository,
                         TicketHistoryRepository ticketHistoryRepository,
                         UserRepository userRepository) {

        this.ticketRepository = ticketRepository;
        this.ticketHistoryRepository = ticketHistoryRepository;
        this.userRepository = userRepository;
    }

    // CREATE
    public TicketResponseDTO createTicket(TicketCreateDTO dto){

        User user = userRepository.findById(dto.getUserId()).orElse(null);

        Ticket ticket = TicketMapper.toEntity(dto, user);

        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(LocalDateTime.now());

        Ticket saved = ticketRepository.save(ticket);

        return TicketMapper.toResponseDTO(saved);
    }

    // GET ALL
    public List<TicketResponseDTO> getAllTickets(){

        return ticketRepository.findAll()
                .stream()
                .map(TicketMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // GET BY ID
    public TicketResponseDTO getTicketById(Long id){

        Ticket ticket = ticketRepository.findById(id).orElse(null);

        if(ticket == null){
            return null;
        }

        return TicketMapper.toResponseDTO(ticket);
    }

    // DELETE
    public String deleteTicket(Long id){

        if (!ticketRepository.existsById(id)){
            return "ticket not found";
        }

        ticketRepository.deleteById(id);

        return "ticket deleted successfully";
    }

    // CHANGE STATUS
    public TicketResponseDTO changeStatus(Long id, TicketStatus newStatus){

        Ticket ticket = ticketRepository.findById(id).orElse(null);

        if(ticket == null){
            return null;
        }

        TicketStatus oldStatus = ticket.getStatus();

        ticket.setStatus(newStatus);

        Ticket updated = ticketRepository.save(ticket);

        TicketHistory history = new TicketHistory(
                "STATUS_CHANGE",
                oldStatus.toString(),
                newStatus.toString(),
                LocalDateTime.now(),
                ticket
        );

        ticketHistoryRepository.save(history);

        return TicketMapper.toResponseDTO(updated);
    }
}