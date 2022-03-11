package ru.edu.helpdesk.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Ticket {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private User client;

    @ManyToOne
    private User support;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status = TicketStatus.OPEN;
}
