package ru.edu.helpdesk.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import ru.edu.helpdesk.service.TicketDaoImpl;

@Configuration
@EnableJpaRepositories("ru.edu.helpdesk.repository")
public class Config {

    @Bean
    public TicketDaoImpl ticketBean(){
        return new TicketDaoImpl();
    }
}
