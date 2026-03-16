package com.issam.ticket_system.service;

import com.issam.ticket_system.dto.TicketCreateDTO;
import com.issam.ticket_system.dto.TicketResponseDTO;
import com.issam.ticket_system.entity.Ticket;
import com.issam.ticket_system.entity.User;
import com.issam.ticket_system.enums.TicketStatus;
import com.issam.ticket_system.repository.TicketHistoryRepository;
import com.issam.ticket_system.repository.TicketRepository;
import com.issam.ticket_system.repository.UserRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TicketServiceTest {

    @Mock
    private TicketRepository ticketRepository;

    @Mock
    private TicketHistoryRepository ticketHistoryRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    void createTicket_shouldCreateTicket() {

        Long userId = 1L;

        TicketCreateDTO dto = new TicketCreateDTO();
        dto.setTitle("sampleTitle");
        dto.setDescription("sampleDescription");
        dto.setUserId(userId);

        User user = new User();
        user.setId(userId);

        when(userRepository.findById(userId))
                .thenReturn(Optional.of(user));

        Ticket savedTicket = new Ticket();
        savedTicket.setId(10L);
        savedTicket.setTitle(dto.getTitle());
        savedTicket.setDescription(dto.getDescription());
        savedTicket.setStatus(TicketStatus.OPEN);
        savedTicket.setUser(user);

        when(ticketRepository.save(any(Ticket.class)))
                .thenReturn(savedTicket);

        TicketResponseDTO result = ticketService.createTicket(dto);

        assertNotNull(result);
        assertEquals("sampleTitle", result.getTitle());
        assertEquals(TicketStatus.OPEN, result.getStatus());

        verify(ticketRepository).save(any(Ticket.class));
    }

    @Test
    void changeStatus_shouldUpdateStatus() {

        Long ticketId = 1L;

        Ticket ticket = new Ticket();
        ticket.setId(ticketId);
        ticket.setStatus(TicketStatus.OPEN);

        when(ticketRepository.findById(ticketId))
                .thenReturn(Optional.of(ticket));

        when(ticketRepository.save(any()))
                .thenReturn(ticket);

        TicketResponseDTO result =
                ticketService.changeStatus(ticketId, TicketStatus.RESOLVED);

        assertEquals(TicketStatus.RESOLVED, result.getStatus());

        verify(ticketHistoryRepository).save(any());
    }
}