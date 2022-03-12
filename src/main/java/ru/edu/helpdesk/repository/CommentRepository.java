package ru.edu.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.helpdesk.entity.Comment;
import ru.edu.helpdesk.entity.Ticket;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByTicket(Ticket ticket);
}