package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class PostService {

    private AtomicInteger atomicInteger = new AtomicInteger(0);

    private final List<Post> posts = new ArrayList<>();

    public PostService() {
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
