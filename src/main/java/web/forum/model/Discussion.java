package web.forum.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.*;
import java.util.Calendar;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
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
}
