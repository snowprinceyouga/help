package ru.edu.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.entity.User;

import java.util.List;

/**
 * Интерфейс репозитория для работы с обращениями
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * @param id
     * @return Список всех обращений конкретного пользователя
     */
    List<Ticket> getAllByClient_Id(long id);

}