package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

/**
 * Service слой отвечает за работу по решистрации нового пользователя
 * с Пользовательским сервисом
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 */
/*@Service*/
public class ServiceReg {

    private UserService userService;

    public ServiceReg(UserService userRepository) {
        this.userService = userRepository;
    }

    /**
     * save new User object in repository
     * @param user Object
     * @return User object
     */
    public User regNewUser(User user) {
        return userService.saveUser(user);
    }
}
