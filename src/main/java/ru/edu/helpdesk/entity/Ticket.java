package ru.edu.helpdesk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;

@Data
@Entity
@NoArgsConstructor
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    @ManyToOne
    @JoinColumn(name = "client_id")
    private User client;

    @ManyToOne
    @JoinColumn(name = "support_id")
    private User support;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status = TicketStatus.OPEN;

}
