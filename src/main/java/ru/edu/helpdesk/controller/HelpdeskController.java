package ru.edu.helpdesk.controller;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.edu.helpdesk.service.CommentServiceImpl;
import ru.edu.helpdesk.service.TicketServiceImpl;

@Log4j2
@Controller
public class HelpdeskController {

    private TicketServiceImpl ticketService;
    private CommentServiceImpl commentService;


    @Autowired
    public void setTicketService(TicketServiceImpl ticketService){this.ticketService = ticketService;}

    @Autowired
    public void setCommentService(CommentServiceImpl commentService){this.commentService = commentService;}

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("tickets", ticketService.allTicketsByClientId(1L));
        return "hello";
    }
    @GetMapping("/ticket/{id}")
    public String ticketInfo(@PathVariable("id") long id, Model model){
        model.addAttribute("ticketinfo", ticketService.ticketInfo(id));
        model.addAttribute("messages", commentService.allMessageByTicketId(id));
        return "ticketInfo";
    }
}