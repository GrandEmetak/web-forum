package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;

@Service
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
