package web.forum.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Модель данных описывающая - Пользователя.
 */
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "password")
    private String password;

    @Column(name = "username")
    private String username;

    @Column(name = "enabled")
    private boolean enabled;

    @OneToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name = "authority_id")
    private Authority authority;

    @OneToMany(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE},
            fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private Set<Post> postSet = new HashSet<>();

    public static User of(int id, String password, String username, boolean enabled) {
        User user = new User();
        user.id = id;
        user.password = password;
        user.username = username;
        user.enabled = enabled;
        return user;
    }

    public static User of(String password, String username, boolean enabled) {
        User user = new User();
        user.password = password;
        user.username = username;
        user.enabled = enabled;
        return user;
    }

    public void addAuthorityToUser(Authority authority) {
        this.authority = authority;
    }

    public void addPostToUser(Post post) {
        this.postSet.add(post);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Set<Post> getPostSet() {
        return postSet;
    }

    public void setPostSet(Set<Post> postSet) {
        this.postSet = postSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("User: id=%s, password=%s, username=%s, enabled=%s, Authority=%s",
                id, password,  username, enabled, authority);
    }
}
