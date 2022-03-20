package ru.edu.helpdesk.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Заявка на тех.поддержку
 *
 */
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
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
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Title title;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status = TicketStatus.OPEN;

    @Column
    private LocalDateTime creationAt = LocalDateTime.now();

}
