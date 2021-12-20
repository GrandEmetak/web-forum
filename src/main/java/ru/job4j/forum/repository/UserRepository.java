package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final List<User> userList = new ArrayList<>();

    public UserRepository() {
        initUser();
    }

    private void initUser() {
        User user = User.of(atomicInteger.incrementAndGet(),
                "$2a$10$7mu7nQOH2V675LSIVeh..ODxMFM8sID7f2jMRwdWVrLfgpZS/zqmG",
                "user",
                true);
        User adm = User.of(atomicInteger.incrementAndGet(),
                "$2a$10$7mu7nQOH2V675LSIVeh..ODxMFM8sID7f2jMRwdWVrLfgpZS/zqmG",
                "root",
                true);
        this.userList.add(user);
        this.userList.add(adm);
    }

    public List<User> getAll() {
        return userList;
    }
}
