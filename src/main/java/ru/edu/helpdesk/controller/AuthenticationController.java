package ru.edu.helpdesk.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.service.AdminService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class AuthenticationController {

    private boolean passMatch = true;

    @Autowired
    private AdminService adminService;

    @GetMapping("/")
    public ModelAndView welcome(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("welcome.html");
        passMatch = true;
        return modelAndView;
    }

    @GetMapping("/registration")
    public String registrationForm(Model model){
        model.addAttribute("passMatch", passMatch);
        return "registration";
    }

    @PostMapping("/registration")
    public void registration(HttpServletResponse response,
                             @RequestParam("firstname") String firstname,
                             @RequestParam("lastname") String lastname,
                             @RequestParam("userName") String userName,
                             @RequestParam("password1") String password1,
                             @RequestParam("password2") String password2) throws IOException {


            if (!password1.equals(password2)){
            passMatch = false;
            response.sendRedirect("/registration");
        }else {
            User user = new User();
            user.setFirstName(firstname);
            user.setLastName(lastname);
            user.setLogin(userName);
            user.setPassword(password1);

            adminService.saveUser(user);
            response.sendRedirect("/login");
        }

    }

}
