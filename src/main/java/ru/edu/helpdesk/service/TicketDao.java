package ru.edu.helpdesk.service;

import ru.edu.helpdesk.entity.Ticket;

import java.util.List;

public interface TicketDao {

    /**
     * Создание ticket
     * @param ticket
     */
    void createTicket(Ticket ticket);

    /**
     * Просмотр инфо по ticket
     * @param id
     * @return Ticket
     */
    Ticket ticketInfo(long id);

    /**
     * найти все ticket по id клиента
     */
    List<Ticket> allTicketsByClientId(long clientId);
}
