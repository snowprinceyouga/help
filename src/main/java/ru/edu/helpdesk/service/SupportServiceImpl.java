package ru.edu.helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.entity.TicketStatus;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.repository.TicketRepository;
import ru.edu.helpdesk.repository.UserRepository;

import java.util.List;

@Service
public class SupportServiceImpl implements SupportService{

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Метод выполняет функцию, взятия в работу.
     * @param ticket
     */
    @Override
    public Ticket workStatusTicket(Ticket ticket) {
        ticket.setStatus(TicketStatus.WORKING);
        ticketRepository.save(ticket);
        return ticket;
    }

    /**
     * Метод выполняет фукнцию, завершения работы
     * @param ticket
     */
    @Override
    public void completeStatusTicket(Ticket ticket) {
        ticket.setStatus(TicketStatus.COMPLETED);
    }

    /**
     * Метод выполняет функцию, отклонения заявки
     */
    @Override
    public void rejectedStatusTicket(Ticket ticket) {
        ticket.setStatus(TicketStatus.REJECTED);
    }


    /**
     * Просмотр инфо по тикету.
     * @param id
     * @return
     */
    @Override
    public Ticket ticketInfo(long id) {
        return ticketRepository.getById(id);
    }

    /**
     * Поиск всех тикетов по статусу.
     * @param ticketStatus
     * @return
     */
    @Override
    public List<Ticket> allTicketByStatus(TicketStatus ticketStatus) {
        return ticketRepository.getTicketByStatus(ticketStatus);
    }

    /**
     * Найти все тикеты.
     * @return
     */
    @Override
    public List<Ticket> allTickets() {
        return ticketRepository.findAll();
    }



    /**
     * Все тикеты суппорта.
     */
    @Override
    public List<Ticket> allTicketsByUser(User user) {

        return ticketRepository.getTicketBySupport(user);
    }

    /**
     * Изменение суппорта
     */
    @Override
    public Ticket swapSupport(Ticket ticket, User user) {
        ticket.setSupport(user);
        return ticketRepository.save(ticket);
    }


}
