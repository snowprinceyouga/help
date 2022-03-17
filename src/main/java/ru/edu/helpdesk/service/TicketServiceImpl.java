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
     * Метод TicketServiceImpl#createTicket(Ticket ticket) создает ticket и добавляет его в БД
     * @param ticket новый ticket
     */
    @Override
    public void createTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    /**
     * Метод TicketServiceImpl#ticketInfo(long id) просматривает информацию по ticket
     * @param id нужного нам ticket
     * @return Ticket искомый нами ticket
     */
    @Override
    public Ticket ticketInfo(long id) {
        return ticketRepository.getById(id);
    }

    /**
     * найти все ticket по id клиента
     */
    @Override
    public List<Ticket> allTicketsByClientId(long clientId) {
        return ticketRepository.getAllByClient_Id(clientId);
    }

    /**
     * Найти все тикеты
     * @return
     */
    @Override
    public List<Ticket> allTickets() {
        return ticketRepository.findAll();
    }
}
