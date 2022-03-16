package ru.edu.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.entity.User;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    List<Ticket> getAllByClient_Id(long id);

}