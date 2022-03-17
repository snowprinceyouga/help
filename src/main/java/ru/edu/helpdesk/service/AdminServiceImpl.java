package ru.edu.helpdesk.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.edu.helpdesk.entity.User;
import ru.edu.helpdesk.repository.UserRepository;

import java.util.List;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private UserRepository userRepository;

    /**
     * Метод AdminServiceImpl#usersList() выводит список всех пользователей сохраненных в БД
     * @return
     */
    @Override
    public List<User> usersList() {
        return userRepository.findAll();
    }

    /**
     * Метод AdminServiceImpl#saveUser(User user) сохраняет нового пользователя в БД
     * @param user новый пользователь для вставки в БД
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * Метод AdminServiceImpl#getUserByUsername(String username) ищет пользователя по логину
     * @param username логин искомого пользователя
     * @return возвращает нужного нам пользователя или NULL если пользователь не найден
     */
    @Override
    public User getUserByUsername(String username) {
        return userRepository.findByLogin(username);
    }
}
