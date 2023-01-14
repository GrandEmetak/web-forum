package web.forum.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.forum.model.Post;
import web.forum.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Repository работает с удаленной БД forum@localhost
 */
@Repository
@Transactional
public class HbmRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(HbmRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * return all Post object from DB order by asc - возрастание
     *
     * @return List<Post>
     */
    public List<Post> getAll() {
        return entityManager.createQuery("from Post as c order by c.id asc", Post.class).getResultList();
    }

    public User findUserById(int id) {
        return entityManager.createQuery("from User as a where id = :Ids", User.class)
                .setParameter("Ids", id)
                .getSingleResult();
    }

    /**
     * Update.jsp Update Post new Data
     *
     * @param post
     * @return
     */
    public Post updatePost(Post post) {
        Post postQ = null;
        postQ = entityManager.find(Post.class, post.getId());
        entityManager.detach(postQ);
        postQ.setDescription(post.getDescription());
        postQ.setName(post.getName());
        entityManager.merge(postQ);
        return postQ;
    }

    /**
     * find User in DB use User.id
     *
     * @param user
     * @return
     */
    public User findByNameUser(String user) {
        User rsl  = new User();
        rsl = entityManager.createQuery("from User as c where c.username = :Ids", User.class)
                .setParameter("Ids", user).getResultList().get(0);
        LOGGER.info("USER -> {}", user);
        if (rsl.getUsername() != null) {
            return rsl;
        }
        return rsl;
    }

    /**
     * find Post object from DB by id.post
     *
     * @param id Post object
     * @return Post obj or null
     */
    public Post findPostById(int id) {
        Post rsl = null;
        rsl = entityManager.find(Post.class, id);
        return rsl;
    }
}
