package web.forum.service;

import web.forum.model.User;

/**
 * Service слой отвечает за работу по регистрации нового пользователя
 * с Пользовательским сервисом
 * Хранение данных в памяти. Базу данных подключать не надо.
 * Отключен в связи с переносом обслуживания в удаленную БД
 */
/*@Service*/
public class ServiceReg {

    private UserService userService;

    public ServiceReg(UserService userRepository) {
        this.userService = userRepository;
    }

    /**
     * save new User object in repository
     *
     * @param user Object
     * @return User object
     */
    public User regNewUser(User user) {
        return userService.saveUser(user);
    }
}
