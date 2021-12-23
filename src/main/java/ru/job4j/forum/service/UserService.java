package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository1;
import ru.job4j.forum.repository.UserRepository1;

import java.util.Collection;

/**
 * Service слой отвечает за работу с репозиториями Пользователи и РолиАвторизации
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 */
/*@Service*/
public class UserService {
    private UserRepository1 userRepository;
    private AuthorityRepository1 authorityRepository;

    public UserService(UserRepository1 userRepository, AuthorityRepository1 authorityRepository) {
        this.userRepository = userRepository;
        this.authorityRepository = authorityRepository;
    }

    public Collection<User> getAll() {
        return userRepository.getAll();
    }

    public User findById(int id) {
        return userRepository.findByIdUser(id);
    }

    public User findByNameUser(String user) {
        return userRepository.findByNameUser(user);
    }

    public Authority findAuthority() {
        return authorityRepository.findByAuthority();
    }

    public User saveUser(User user) {
        var aut = findAuthority();
        user.setAuthority(aut);
        return userRepository.saveNewUser(user);
    }
}
