package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import ru.edu.helpdesk.entity.Comment;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.entity.UserRole;
import ru.edu.helpdesk.security.HelpdeskUserPrincipal;
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
    public String index(Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {

        if (principal != null) {
            final User current = principal.getUser();
            final List<Ticket> tickets = ticketService.allTicketsByClientId(current.getId());
            model.addAttribute("current", current);
            }
        return "hello";
    }

    @GetMapping("/ticket")
    public String ticketView(Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal){
        if (principal != null) {
            final User current = principal.getUser();
            final List<Ticket> tickets;
            if (current.getRole() == UserRole.USER) {
                tickets = ticketService.allTicketsByClientId(current.getId());
            } else {
                tickets = ticketService.allTickets();
            }
            model.addAttribute("current", current);
            model.addAttribute("tickets", tickets);
        }
        return "ticket";
    }

    @GetMapping("/ticket/{id}")
    public String ticketInfo(@PathVariable("id") long id, Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {

        final User current = principal.getUser();
        model.addAttribute("current", current);
        model.addAttribute("ticketInfo", ticketService.ticketInfo(id));
        model.addAttribute("messages", commentService.allMessageByTicketId(id));
        return "ticketInfo";
    }


    @GetMapping("/ticket/new")
    public String ticketInfo(Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        final User current = principal.getUser();
        model.addAttribute("current", current);
        model.addAttribute("ticket",new Ticket());
        return "newTicket";
    }

    @PostMapping("/ticket/new")
    public String createTicket(@ModelAttribute Ticket ticket, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        final User current = principal.getUser();
        ticket.setClient(current);
        ticketService.createTicket(ticket);
        return "redirect:/ticket";
    }
}