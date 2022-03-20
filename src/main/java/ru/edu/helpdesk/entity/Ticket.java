package ru.edu.helpdesk.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

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
    private String description;

    @Enumerated(EnumType.ORDINAL)
    private Title title;

    @Enumerated(EnumType.ORDINAL)
    private TicketStatus status = TicketStatus.OPEN;

    @Column
    private String creationAt = dateFormat();

    /**
     * Красивая дата
     * @return
     */
    private String dateFormat() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd-MM-yyyy");
        String formatDateTime = now.format(formatter);
        return formatDateTime;
    }


}
