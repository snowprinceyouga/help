package helpdesk.service;

import helpdesk.pojo.Ticket;

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
    Ticket ticketInfo(Integer id);
}
