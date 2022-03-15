package ru.edu.helpdesk.service;


import ru.edu.helpdesk.entity.Comment;

import java.util.List;

public interface CommentService {

    List<Comment> allMessageByTicketId (long id);
}
