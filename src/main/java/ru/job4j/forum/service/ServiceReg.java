package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.UserRepository;

@Service
public class ServiceReg {

    private UserRepository userRepository;

    public ServiceReg(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * save new User object in repository
     * @param user Object
     * @return User object
     */
    public User regNewUser(User user) {
        return userRepository.saveNewUser(user);
    }
}
