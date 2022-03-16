package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.security.HelpdeskUserPrincipal;
import ru.edu.helpdesk.service.AdminService;

import java.util.List;

@Controller
@Secured("ROLE_ADMIN")
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping()
    public String index(Model model, @AuthenticationPrincipal HelpdeskUserPrincipal principal) {
        if (principal != null) {
            final User current = principal.getUser();
            final List<User> users = adminService.usersList();
            model.addAttribute("current", current);
            model.addAttribute("users", users);
        }
        return "admin/index";
    }
}
