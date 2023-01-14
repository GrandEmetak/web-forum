package web.forum.repository;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import web.forum.model.User;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ЛОКАЛЬНЫЙ РЕПОЗИТОРИЙ - Репозиторий Пользователей (Данные о пользователе)
  * сделан не активным репозиторий так как произведен переход на JDBC *
 */
@Repository
public class UserRepositoryLocal {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private Map<Integer, User> userMap = new HashMap<>();

        private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public UserRepositoryLocal() {
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
        this.userMap.put(user.getId(), user);
        this.userMap.put(adm.getId(), adm);
    }

/**/
    public Collection<User> getAll() {
        return userMap.values();
    }

    public User findByIdUser(int id) {
        return userMap.get(id);
    }

    public User findByNameUser(String user) {
       return userMap.values()
                .stream()
                .filter(user1 -> user1.getUsername().equals(user))
                .findAny()
                .orElse(null);
    }

    /**
     * Кодирование пароля и сохранение Юзера в БД
     * @param user object
     * @return user object
     */
    public User saveNewUser(User user) {
        user.setId(atomicInteger.incrementAndGet());
        user.setPassword(encoder.encode(user.getPassword()));
        user.setEnabled(true);
       return userMap.put(user.getId(), user);
    }

}
