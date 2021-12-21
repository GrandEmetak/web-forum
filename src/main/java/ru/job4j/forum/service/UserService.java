package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;

import java.util.Collection;
import java.util.List;

@Service
public class UserService {
    private UserRepository userRepository;
    private AuthorityRepository authorityRepository;

    public UserService(UserRepository userRepository, AuthorityRepository authorityRepository) {
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
