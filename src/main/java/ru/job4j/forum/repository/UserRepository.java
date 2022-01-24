package ru.job4j.forum.repository;

import org.springframework.data.repository.CrudRepository;

import ru.job4j.forum.model.User;

/**
 * 2. Регистрация пользователя [#296069 #241520]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.4. Security
 */
public interface UserRepository  extends CrudRepository<User, Integer> {
    public User findUserByUsername(String userName);
}
