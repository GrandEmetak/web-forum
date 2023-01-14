package web.forum.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Calendar;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "discussion")
public class Discussion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id;

    @Column(name = "description")
    private String description;

    @Column(name = "created")
    private Calendar created;

    @ManyToOne(cascade = {CascadeType.PERSIST,
            CascadeType.DETACH,
            CascadeType.PERSIST,
            CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;

    public Discussion() {
    }

    public static Discussion of(String description, User user) {
        Discussion discussion = new Discussion();
        discussion.description = description;
        discussion.user = user;
        return discussion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Discussion that = (Discussion) o;
        return id != 0 && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
