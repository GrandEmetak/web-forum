package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.PostRepository;

import java.util.Collection;

/**
 * уровень Сервис для работы с репозиторием объявлений Пост (Post object)
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 */
@Service
public class PostService {

    private PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Collection<Post> getAll() {
        return postRepository.getAll();
    }

    /**
     * find By Id Post Object
     *
     * @param id Post Object
     * @return Post Object
     */
    public Post findById(int id) {
        return postRepository.findByIdPost(id);
    }

    public Post putUserToPost(Post post, User user) {
        Post post1 = Post.of(post.getName(), post.getDescription());
        post1.setUser(user);
        return post1;
    }

    /**
     * save Post Object in Repo
     *
     * @param post Post Object
     */
    public void save(Post post) {
        postRepository.save(post);
    }

    /**
     * save new data in Post Object
     *
     * @param post Post Object
     * @return Post Object
     */
    public Post saveData(Post post) {
        postRepository.updatePost(post);
        return post;
    }

    /**
     * update Post object
     *
     * @param post Post Object include new info
     * @return Post Object
     */
    public Post updatePost(Post post) {
        return postRepository.updatePost(post);
    }
}
