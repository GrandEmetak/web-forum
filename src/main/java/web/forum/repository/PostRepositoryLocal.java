package web.forum.repository;

import org.springframework.stereotype.Repository;
import web.forum.model.Post;
import web.forum.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Local Repository - Репозиторий отвечает за работу с объектами Пост/Post
 * Хранение данных в памяти.
 */
@Repository
public class PostRepositoryLocal {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private Map<Integer, Post> postMap = new HashMap<>();

    public PostRepositoryLocal() {
        initPost();
    }

    private void initPost() {
        User user = new User();
        user.setUsername("Ivan Sobolev");
        Post post = Post.of(atomicInteger.incrementAndGet(),
                "Продам велосипед",
                "Продам велосипед, новый, горный, 25 скоростей Schimano");
        post.addUserToPost(user);

        User user1 = new User();
        user1.setUsername("Svetlana Donovan");
        Post post1 = Post.of(atomicInteger.incrementAndGet(),
                "Куплю мотоцикл",
                "Куплю или приму в дар мотоцикл, б/у, находу");
        post1.addUserToPost(user1);
        this.postMap.put(post.getId(), post);
        this.postMap.put(post1.getId(), post1);
    }

    /**
     * save new Post object in to the storage
     *
     * @param post
     * @return
     */
    public Post save(Post post) {
        post.setId(atomicInteger.incrementAndGet());
        return postMap.put(post.getId(), post);
    }

    /**
     * @return Collection Post object
     */
    public Collection<Post> getAll() {
        return postMap.values();
    }

    /**
     * find Post object by id
     *
     * @param id Post object(ArrayList index)
     * @return Post Object
     */
    public Post findByIdPost(int id) {
        return postMap.get(id);
    }

    /**
     * update Post object include new info about object
     *
     * @param post Object
     * @return post Object
     */
    public Post updatePost(Post post) {
        var post1 = postMap.get(post.getId());
        post.setCreated(post1.getCreated());
        post.setUser(post1.getUser());
        return postMap.put(post.getId(), post);
    }

}
