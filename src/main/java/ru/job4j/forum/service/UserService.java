package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Service слой отвечает за работу с репозиториями Пользователи и РолиАвторизации
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 * !!! IMPORTANT
 * Удалено использование
 * private AuthorityRepository1 authorityRepository;
 * private UserRepository1 userRepository;
 * public Collection<User> getAll() {
 * return userRepository.getAll();
 * }
 * это был локальный репозиторий - хранение в памяти
 * методы
 * public Authority findAuthority() {
 * return authorityRepository.findByAuthority();
 * }
 * public User saveUser(User user) {
 * Authority aut = findAuthority();
 * user.setAuthority(aut);
 * return userRepository.save(user);
 * public Collection<User> getAll() {
 * return userRepository.getAll();
 * }
 * }
 */
@Service
public class UserService {

    private UserRepository userRepositoryStore;
    private AuthorityRepository authorityRepositoryStore;
    private HbmRepository hbmRepository;

    public UserService(UserRepository userRepositoryStore,
                       AuthorityRepository authorityRepositoryStore,
                       HbmRepository hbmRepository) {
        this.userRepositoryStore = userRepositoryStore;
        this.authorityRepositoryStore = authorityRepositoryStore;
        this.hbmRepository = hbmRepository;
    }

    /**
     * get all User from DB forum@localhost table users
     *
     * @return List<User> object
     */
    public Collection<User> getAll() {
        List<User> userList = new ArrayList<>();
        var iterAllUser = userRepositoryStore.findAll();
        while (iterAllUser.iterator().hasNext()) {
            userList.add(iterAllUser.iterator().next());
        }
        return userList;
    }

    /**
     * find User by id
     *
     * @param id
     * @return User
     */
    public User findUserById(int id) {
        return hbmRepository.findUserById(id);
    }

    public User findByNameUser(String user) {
        return hbmRepository.findByNameUser(user);
    }

    public Authority findAuthority(Authority authority) {
        var optionalAuthority = authorityRepositoryStore.findById(authority.getId());
        return optionalAuthority.orElse(null);
    }

    /**
     * method  automatically assigns a role to a user (ROLE_USER),
     * save User object in DB table users
     *
     * @param user
     * @return
     */
    public User saveUser(User user) {
        Authority aut = Authority.of(1, "ROLE_USER");
        user.setAuthority(aut);
        return userRepositoryStore.save(user);
    }

    public User findUserByUsername(String userName) {
        return userRepositoryStore.findUserByUsername(userName);
    }
}
