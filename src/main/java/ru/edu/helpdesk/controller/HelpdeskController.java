package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.repository.TicketRepository;

@RestController
public class HelpdeskController {

    @Autowired
    private TicketRepository ticketRepository;

    @GetMapping("/")
    public String index() {
        final Ticket ticket = ticketRepository.getById(1L);

        return "Greetings from Spring Boot!" + ticket;
    }
}