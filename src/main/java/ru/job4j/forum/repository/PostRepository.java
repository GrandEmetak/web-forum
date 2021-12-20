package ru.job4j.forum.repository;

import org.springframework.stereotype.Repository;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class PostRepository {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final List<Post> posts = new ArrayList<>();

    public PostRepository() {
        initPost();
    }

    private void initPost() {
        User user = new User();
        user.setUsername("Ivan Sobolev");
        Post post = Post.of(atomicInteger.incrementAndGet(),
                "Продам велосипед",
                "Продам велосипед, новый, горный, 25 скоростей Schimano");
        post.addUserToPost(user);
        this.posts.add(post);
    }

    public List<Post> getAll() {
        return posts;
    }

}
