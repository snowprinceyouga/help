package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.service.AdminService;

import java.util.List;

@RestController
@RequestMapping(value = "/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping
    public List<User> index() {
        return adminService.usersList();
    }
}
