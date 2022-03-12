package ru.edu.helpdesk.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    private Ticket ticket;

    @ManyToOne
    private User user;

    @Column(nullable = false)
    private String message;
}
