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

/**
 * Контроллер для пользователя user
 * Главное меню, просмотр всех своих заявок, информация о конкретной заявке, создание новой заявки
 */
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

    /**
     * Get запрос на главную страницу приложения после аутентификации.
     * Авторизованный пользователь видит список только своих заявок и имеет доступ ко всем сервисам
     * @param model
     * @param principal
     * @return
     */
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

    /**
     * Get запрос на страницу с подробной информацией о конкретной заявке.
     * @param id
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/ticket/{id}")
    public String ticketInfo(@PathVariable("id") long id, Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {

        final User current = principal.getUser();
        model.addAttribute("current", current);
        model.addAttribute("ticketInfo", ticketService.ticketInfo(id));
        model.addAttribute("messages", commentService.allMessageByTicketId(id));
        model.addAttribute("comment",new Comment());
        return "ticketInfo";
    }

    /**
     * Get запрос на страницу создания нового обращения.
     * @param model
     * @param principal
     * @return
     */
    @GetMapping("/ticket/new")
    public String ticketInfo(Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        final User current = principal.getUser();
        model.addAttribute("current", current);
        model.addAttribute("ticket",new Ticket());
        return "newTicket";
    }

    /**
     * Post запрос - создание в базе данных (таблице ticket) нового обращения и возврат в главное меню
     * @param ticket
     * @param principal
     * @return
     */
    @PostMapping("/ticket/new")
    public String createTicket(@ModelAttribute Ticket ticket, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        final User current = principal.getUser();
        ticket.setClient(current);
        ticketService.createTicket(ticket);
        return "redirect:/ticket";
    }

    @PostMapping("/ticket/{id}")
    public String createComment(@PathVariable("id") long id, @ModelAttribute Comment comment, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        final User current = principal.getUser();

        comment.setTicket(ticketService.ticketInfo(id));
        comment.setUser(current);
        comment.setId(null);
        commentService.createComment(comment);

        String path = "redirect:/ticket/" + id;
        return path;
    }
}