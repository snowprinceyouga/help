package ru.edu.helpdesk.service;

import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.entity.TicketStatus;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.entity.UserRole;

import java.util.List;

public interface SupportService {

    /**
     * Взять в работу
     * @param ticket
     */
    Ticket workStatusTicket(Ticket ticket);

    /**
     * Метод выполняет фукнцию, завершения работы
     * @param ticket
     */
    void completeStatusTicket(Ticket ticket);


    /**
     * Метод выполняет функцию, отклонения заявки
     */
    void rejectedStatusTicket(Ticket ticket);
    /**
     * Просмотр инфо по ticket
     * @param id
     * @return Ticket
     */
    Ticket ticketInfo(long id);

    /**
     * Поиск тикетов по статусу
     * @param status
     * @return
     */
    List<Ticket> allTicketByStatus(TicketStatus ticketStatus);

    /**
     * Найти все тикеты
     */
    List<Ticket> allTickets();


    /**
     * Все тикеты суппорта.
     */
    List<Ticket> allTicketsByUser(User user);


    /**
     * Изменение суппорта
     */
    Ticket swapSupport(Ticket ticket, User user);


}
