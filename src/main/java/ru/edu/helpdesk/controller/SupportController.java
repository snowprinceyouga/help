package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.entity.TicketStatus;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.entity.UserRole;
import ru.edu.helpdesk.security.HelpdeskUserPrincipal;
import ru.edu.helpdesk.service.CommentServiceImpl;
import ru.edu.helpdesk.service.SupportService;
import ru.edu.helpdesk.service.TicketServiceImpl;

import java.util.List;

@Controller
@Secured({"ROLE_SUPPORT", "ROLE_ADMIN"})
@RequestMapping(value = "/support")
public class SupportController {

    private TicketServiceImpl ticketService;
    private CommentServiceImpl commentService;
    private SupportService supportService;

    @Autowired
    public void setTicketService(TicketServiceImpl ticketService) {
        this.ticketService = ticketService;
    }

    @Autowired
    public void setCommentService(CommentServiceImpl commentService) {
        this.commentService = commentService;
    }

    @Autowired
    public void setSupportService(SupportService supportService) {
        this.supportService = supportService;
    }

    @GetMapping()
    public String index(Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        if (principal != null) {
            final User current = principal.getUser();
            final List<Ticket> allTickets;
            if (current.getRole() == UserRole.ADMIN) {
                allTickets = supportService.allTickets();
            } else {
                allTickets = supportService.allTicketsByUser(current);
            }
            model.addAttribute("current", current);
            model.addAttribute("tickets", allTickets);
        }
        return "support/support";
    }

    @GetMapping("/openTickets")
    public String working(Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        if (principal != null) {
            final User current = principal.getUser();
            final List<Ticket> allTickets = supportService.allTicketByStatus(TicketStatus.OPEN);

            model.addAttribute("current", current);
            model.addAttribute("tickets", allTickets);
        }
        return "support/openTickets";
    }


    @GetMapping("/ticketwork")
    public String ticketView(Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
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
        return "/support/ticketwork";
    }

    @GetMapping("/ticketsupport/{id}")
    public String ticketWork(@PathVariable("id") long id, Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        final User current = principal.getUser();

        model.addAttribute("current", current);
        model.addAttribute("ticketInfo", ticketService.ticketInfo(id));
        model.addAttribute("messages", commentService.allMessageByTicketId(id));

        return "/support/ticketsupport";
    }

    @PostMapping("ticketsupport/{id}")
    public String workWithTicket(@PathVariable("id") long id, @AuthenticationPrincipal HelpdeskUserPrincipal principal){
        System.out.println("postmapping");
        final User current = principal.getUser();
        supportService.swapSupport(ticketService.ticketInfo(id), current);
        supportService.workStatusTicket(ticketService.ticketInfo(id));
        return "/ticketsupport/{id}";
    }
}
