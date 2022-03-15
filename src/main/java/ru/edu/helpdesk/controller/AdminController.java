package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.service.AdminService;

import java.security.Principal;
import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public String index(Model model, Principal principal) {
        if (principal != null) {
            final String login = principal.getName();
            final List<User> users = adminService.usersList();
            model.addAttribute("username", login);
            model.addAttribute("users", users);
        } else {
            model.addAttribute("username", "Anonymous");
        }
        return "admin/index";
    }
}
