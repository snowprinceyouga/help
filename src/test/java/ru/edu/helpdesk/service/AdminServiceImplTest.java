package ru.edu.helpdesk.service;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit4.SpringRunner;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.entity.UserRole;
import ru.edu.helpdesk.repository.UserRepository;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

@RunWith(SpringRunner.class)
public class AdminServiceImplTest {

    @TestConfiguration
    static class Config {
        @Bean
        public AdminService adminService() {
            return new AdminServiceImpl();
        }
    }

    @Autowired
    private AdminService adminService;

    @MockBean
    private UserRepository userRepository;

    private final User ADMIN = new User(1L, "admin", "admin", "admin", "admin", UserRole.ADMIN);
    private final User SUPPORT = new User(2L, "support", "support", "support", "support", UserRole.SUPPORT);
    private final User USER1 = new User(3L, "user1", "user1", "user1", "user1", UserRole.USER);
    private final User USER2 = new User(4L, "user2", "user2", "user2", "user2", UserRole.USER);

    private final List<User> USERS = Arrays.asList(ADMIN, SUPPORT, USER1, USER2);

    @Before
    public void setUp() {
        Mockito.when(userRepository.findAll()).thenReturn(USERS);
    }

    @Test
    public void usersList() {
        assertIterableEquals(USERS, adminService.usersList());
    }
}