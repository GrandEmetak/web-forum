package web.forum.repository;

import org.springframework.stereotype.Repository;
import web.forum.model.Authority;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ЛОКАЛЬНЫЙ РЕПОЗИТОРИЙ - Репозиторий Ролей авторизации
 * репозиторий сделан не активным
 */
@Repository
public class AuthorityRepositoryLocal {
    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private Map<Integer, Authority> authorityMap = new HashMap<>();

    public AuthorityRepositoryLocal() {
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
