package web.forum.model;

import java.util.Objects;
import javax.persistence.*;

/**
 * Модель данных описывающая - Роль авторизации пользователя
 */
@Entity
@Table(name = "authoritys")
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "authority")
    private String authority;

    public Authority() {
    }

    public static Authority of(String authority) {
        Authority auth = new Authority();
        auth.authority = authority;
        return auth;
    }

    public static Authority of(int id, String authority) {
        Authority auth = new Authority();
        auth.id = id;
        auth.authority = authority;
        return auth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority authority = (Authority) o;
        return id == authority.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Authority{"
                + "id=" + id
                + ", authority='" + authority + '\''
                + '}';
    }
}
