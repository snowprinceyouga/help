package ru.edu.helpdesk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.edu.helpdesk.entity.User;

/**
 * Интерфейс репозитория для работы с пользователями приложения
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * @param login
     * @return Пользователь по логину
     */
    User findByLogin(String login);

}