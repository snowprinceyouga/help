package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.service.CommentServiceImpl;
import ru.edu.helpdesk.service.TicketServiceImpl;

import java.security.Principal;
import java.util.List;

@Controller
public class HelpdeskController {

    private TicketServiceImpl ticketService;
    private CommentServiceImpl commentService;

    @Autowired
    public void setTicketService(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setCommentService(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @GetMapping()
    public String index(Model model, Principal principal) {

        if (principal != null) {
            final String login = principal.getName();
            final List<Ticket> tickets = ticketService.allTicketsByLogin(login);
            model.addAttribute("username", login);
            model.addAttribute("tickets", tickets);
        } else {
            model.addAttribute("username", "Anonymous");
        }
        return "hello";
    }

    @GetMapping("/ticket/{id}")
    public String ticketInfo(@PathVariable("id") long id, Model model) {
        model.addAttribute("ticketinfo", ticketService.ticketInfo(id));
        model.addAttribute("messages", commentService.allMessageByTicketId(id));
        return "ticketInfo";
    }
}