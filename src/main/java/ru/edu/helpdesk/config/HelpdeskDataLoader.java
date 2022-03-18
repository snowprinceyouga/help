package ru.edu.helpdesk.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ru.edu.helpdesk.entity.*;
import ru.edu.helpdesk.repository.CommentRepository;
import ru.edu.helpdesk.repository.TicketRepository;
import ru.edu.helpdesk.repository.UserRepository;


@Component
public class HelpdeskDataLoader implements ApplicationRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void run(ApplicationArguments args) {

        if (userRepository.count() > 0) {
            return;
        }

        User admin = createUser("admin", "admin", UserRole.ADMIN, "Alex", "Sadikov");
        User support = createUser("support", "support", UserRole.SUPPORT, "Artem", "Mikishev");
        User user1 = createUser("user1", "user1", UserRole.USER, "Olga", "Lapenok");
        User user2 = createUser("user2", "user2", UserRole.USER, "Sergey", "Malyshev");

        Ticket ticket1 = createTicket(Title.Problem_with_PC,"1111", TicketStatus.WORKING, user1, support);
        Ticket ticket2 = createTicket(Title.Problem_with_NETWORK,"1111", TicketStatus.OPEN, user1, support);
        Ticket ticket3 = createTicket(Title.Problem_with_NETWORK,"111", TicketStatus.OPEN, user2, null);

        createComment(ticket1, user1, "comment ticket1");
        createComment(ticket2, user1, "comment ticket2");
        createComment(ticket1, support, "comment ticket11");
        createComment(ticket1, support, "comment ticket111");
        createComment(ticket2, support, "comment ticket22");
        createComment(ticket3, user2, "comment ticket3");
    }

    private User createUser(String login, String password, UserRole role, String firstName, String lastName) {
        String hashedPassword = passwordEncoder.encode(password);
        User user = new User();
        user.setLogin(login);
        user.setPassword(hashedPassword);
        user.setRole(role);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        return userRepository.save(user);
    }

    private Ticket createTicket(Title title, String description, TicketStatus status, User client, User support) {
        Ticket ticket = new Ticket();
        ticket.setTitle(title);
        ticket.setDescription(description);
        ticket.setStatus(status);
        ticket.setClient(client);
        ticket.setSupport(support);
        return ticketRepository.save(ticket);
    }

    private Comment createComment(Ticket ticket, User user, String message) {
        Comment comment = new Comment();
        comment.setTicket(ticket);
        comment.setUser(user);
        comment.setMessage(message);
        return commentRepository.save(comment);
    }
}
