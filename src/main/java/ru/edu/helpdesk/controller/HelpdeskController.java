package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.helpdesk.entity.Ticket;
import ru.edu.helpdesk.repository.TicketRepository;
import ru.edu.helpdesk.service.TicketDaoImpl;

@Controller
public class HelpdeskController {

    private TicketDaoImpl ticketDao;

    @Autowired
    public void setTicketDao(TicketDaoImpl ticketDao){this.ticketDao = ticketDao;}

    @GetMapping()
    public String index(Model model) {
        model.addAttribute("tickets", ticketDao.allTicketsByClientId(1L));
        return "hello";
    }
    @GetMapping("/ticket/{id}")
    public String ticketInfo(@PathVariable("id") long id, Model model){
        model.addAttribute("ticketinfo", ticketDao.ticketInfo(id));
        return "ticketInfo";
    }
}