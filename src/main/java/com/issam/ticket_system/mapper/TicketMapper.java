package com.issam.ticket_system.mapper;

import com.issam.ticket_system.dto.TicketCreateDTO;
import com.issam.ticket_system.dto.TicketResponseDTO;
import com.issam.ticket_system.entity.Ticket;
import com.issam.ticket_system.entity.User;

public class TicketMapper {

    public static Ticket toEntity(TicketCreateDTO dto, User user){

        Ticket ticket = new Ticket();

        ticket.setTitle(dto.getTitle());
        ticket.setDescription(dto.getDescription());
        ticket.setUser(user);

        return ticket;
    }

    public static TicketResponseDTO toResponseDTO(Ticket ticket){

        TicketResponseDTO dto = new TicketResponseDTO();

        dto.setId(ticket.getId());
        dto.setTitle(ticket.getTitle());
        dto.setDescription(ticket.getDescription());
        dto.setStatus(ticket.getStatus());
        dto.setCreatedAt(ticket.getCreatedAt());

        if(ticket.getUser() != null){
            dto.setUserId(ticket.getUser().getId());
        }

        return dto;
    }
}