package web.forum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;
import web.forum.model.Discussion;
import web.forum.model.Post;

import web.forum.model.User;
import web.forum.repository.HbmRepository;
import web.forum.store.PostRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Сервис для работы с репозиторием объявлений Пост (Post object)
 * Реализованы варианты работы как :
 * через Spring
 * через Hibernate
 * <p>
 * не активные методы относятся к локальному классу репозиторию
 */
@Service
public class PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    private static final Marker DEBUG = MarkerFactory.getMarker("DEBUG");

    private final PostRepository postRepository;

    private final HbmRepository hbmRepository;

    public PostService(PostRepository posts,
                       HbmRepository hbmRepository) {
        this.postRepository = posts;
        this.hbmRepository = hbmRepository;
    }

    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        postRepository.findAll().forEach(rsl::add);
        LOGGER.debug(DEBUG, " getAll() Post List {}", rsl);
        LOGGER.info("info message {}", rsl);
        return rsl;
    }

    /**
     * find By Id Post Object
     * important int id convert Long.valueOf(id))
     *
     * @param id Post Object
     * @return Post Object
     */
    public Post findById(int id) {
        return hbmRepository.findPostById(id);
    }

    public Post putUserToPost(Post post, User user) {
        Post post1 = Post.of(post.getName(), post.getDescription());
        post1.setUser(user);
        return post1;
    }

    /**
     * save Post Object in DB Repo
     * used CrudRepository
     *
     * @param post Post Object
     */
    public Post save(Post post) {
        return postRepository.save(post);
    }

    /**
     * save new data in Post Object
     *
     * @param post Post Object
     * @return Post Object
     */
    public Post updatePost(Post post) {
        hbmRepository.updatePost(post);
        return post;
    }

    public void putDiscToPost(Post post, Discussion discussion) {
        post.addDiscussion(discussion);
    }
}
