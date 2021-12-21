package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Authority;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class AuthorityRepository {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private Map<Integer, Authority> authorityMap = new HashMap<>();

    public AuthorityRepository() {
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
