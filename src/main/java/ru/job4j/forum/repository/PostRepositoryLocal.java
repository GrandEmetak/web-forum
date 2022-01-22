package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Репозиторий отвечает за работу с объектами Пост/Post
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
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
