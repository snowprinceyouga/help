package ru.edu.helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.helpdesk.entity.Comment;
import ru.edu.helpdesk.repository.CommentRepository;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentRepository commentRepository;


    /**
     * Метод CommentServiceImpl#allMessageByTicketId(long id) выводит все комментарии нужного Тикета
     * @param id нужного нам Тикета
     * @return
     */
    @Override
    public List<Comment> allMessageByTicketId(long id) {
        return commentRepository.getCommentsByTicket_Id(id);
    }

    /**
     * Метод CommentServiceImpl#createComment(Comment comment) создает коменатрий и добавляет его в БД
     * @param comment новый коментарий
     */
    public void createComment(Comment comment){
        commentRepository.save(comment);
    }
}
