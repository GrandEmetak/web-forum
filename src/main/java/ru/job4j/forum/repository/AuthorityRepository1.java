package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * Репозиторий Ролей авторизации
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 * 2. Spring boot security [#296071]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * - Подключите Spring Security к проекту.
 * - Сделайте сразу интеграцию с базой данных.
 * репозиторий сделан не активным
 */
@Repository
public class AuthorityRepository1 {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private Map<Integer, Authority> authorityMap = new HashMap<>();

    public AuthorityRepository1() {
        initUser();
    }

    private void initUser() {
        Authority user = Authority.of(atomicInteger.incrementAndGet(),
                "USER");
        Authority admin = Authority.of(atomicInteger.incrementAndGet(), "ADMIN");
        this.authorityMap.put(user.getId(), user);
        this.authorityMap.put(admin.getId(), admin);
    }

    public Authority findByAuthority(Authority authority) {
        return authorityMap.values().stream()
                .filter(authority1 -> authority1.getAuthority().equals(authority.getAuthority()))
                .findFirst()
                .orElse(null);
    }

    public Authority findByAuthority() {
        return authorityMap.values().stream()
                .filter(authority1 -> authority1.getAuthority().equals("USER"))
                .findFirst()
                .orElse(null);
    }
}
