package ru.edu.helpdesk.service;


import ru.edu.helpdesk.entity.Comment;

import java.util.List;

public interface CommentService {

    /**
     * Выводит все комментарии заданного Тикета
     * @param id
     * @return
     */
    List<Comment> allMessageByTicketId (long id);
}
