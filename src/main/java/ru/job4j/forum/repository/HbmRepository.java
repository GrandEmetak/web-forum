package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
@Transactional
public class HbmRepository {

    @PersistenceContext
    private EntityManager entityManager;

    /**
     * return all Post object from DB order by asc - возрастание
     *
     * @return  List<Post>
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
        System.out.println("User findByNameUser(String user) {" + user);
        System.out.println("IS OPEN? " + entityManager.isOpen());
        return entityManager.createQuery("from User as c where c.username = :Ids", User.class)
                .setParameter("Ids", user)
                .getSingleResult();
    }

    /**
     * find Post object from DB by id.post
     * @param id Post object
     * @return Post obj or null
     */
    public Post findPostById(int id) {
        Post rsl = null;
        rsl = entityManager.find(Post.class, id);
        return rsl;
    }
}
