package ru.edu.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.helpdesk.entity.Comment;
import ru.edu.helpdesk.entity.Ticket;

import java.util.List;

/**
 * Интерфейс репозитория для работы с комментариями
 */
@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    /**
     * @param id
     * @return Список всех комментариев по конкретному обращению
     */
    List<Comment> getCommentsByTicket_Id(Long id);
}