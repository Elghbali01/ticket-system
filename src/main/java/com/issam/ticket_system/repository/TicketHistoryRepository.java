package com.issam.ticket_system.repository;

import com.issam.ticket_system.entity.TicketHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketHistoryRepository extends JpaRepository<TicketHistory, Long> {
}