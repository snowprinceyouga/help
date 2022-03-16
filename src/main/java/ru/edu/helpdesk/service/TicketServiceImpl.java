package ru.edu.helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.repository.TicketRepository;
import ru.edu.helpdesk.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Service
public class TicketServiceImpl implements TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Создание ticket
     *
     * @param ticket
     */
    @Override
    public void createTicket(Ticket ticket) {

    }

    /**
     * Просмотр инфо по ticket
     *
     * @param id
     * @return Ticket
     */
    @Override
    public Ticket ticketInfo(long id) {
        return ticketRepository.getById(id);
    }

    @Override
    public List<Ticket> allTicketsByClientId(long clientId) {
        return ticketRepository.getAllByClient_Id(clientId);
    }
}
