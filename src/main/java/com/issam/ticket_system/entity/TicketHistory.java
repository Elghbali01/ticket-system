package com.issam.ticket_system.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class TicketHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String actionType;

    private String oldValue;

    private String newValue;

    private LocalDateTime changedAt;

    @ManyToOne
    @JoinColumn(name = "ticket_id")
    private Ticket ticket;

    public TicketHistory() {
    }

    public TicketHistory(String actionType, String oldValue, String newValue, LocalDateTime changedAt, Ticket ticket) {
        this.actionType = actionType;
        this.oldValue = oldValue;
        this.newValue = newValue;
        this.changedAt = changedAt;
        this.ticket = ticket;
    }

    public Long getId() {
        return id;
    }

    public String getActionType() {
        return actionType;
    }

    public String getOldValue() {
        return oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public LocalDateTime getChangedAt() {
        return changedAt;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public void setChangedAt(LocalDateTime changedAt) {
        this.changedAt = changedAt;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }
}