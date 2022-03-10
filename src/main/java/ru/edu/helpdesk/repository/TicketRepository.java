package ru.edu.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.helpdesk.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

}