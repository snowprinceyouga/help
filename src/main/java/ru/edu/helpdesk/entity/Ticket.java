package ru.edu.helpdesk.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
public class Ticket implements Serializable {
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
    private TicketStatus status;
}
