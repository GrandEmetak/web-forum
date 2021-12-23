package ru.job4j.forum.service;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.store.PostRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * уровень Сервис для работы с репозиторием объявлений Пост (Post object)
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 * 1. Spring boot repository [#2095]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * Подключите базу данных в проекте job4j_forum.
 * private final PostRepository posts - произведена замена,
 * с локального репозитория класс ru.job4j.forum.repository.PostRepository, на
 * interface PostRepository -ru.job4j.forum.store -
 * ! Service  больше не работает с лок.репозиторием,
 * private PostRepository postRepository;
 * <p>
 * public PostService(PostRepository postRepository) {
 * this.postRepository = postRepository;
 * }
 * а работает с репозит потдерживающим БД (Postgres)
 * не активные методы относятся к локальному классу репозиторию
 */
@Service
public class PostService {
    private final PostRepository posts;

    public PostService(PostRepository posts) {
        this.posts = posts;
    }

    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        posts.findAll().forEach(rsl::add);
        return rsl;
    }

/*    public Collection<Post> getAll() {
        return postRepository.getAll();
    }

    /**
     * find By Id Post Object
     *
     * @param id Post Object
     * @return Post Object
     */
  /*  public Post findById(int id) {
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
  /*  public void save(Post post) {
        postRepository.save(post);
    }

    /**
     * save new data in Post Object
     *
     * @param post Post Object
     * @return Post Object
     */
  /*  public Post saveData(Post post) {
        postRepository.updatePost(post);
        return post;
    }

    /**
     * update Post object
     *
     * @param post Post Object include new info
     * @return Post Object
     */
  /*  public Post updatePost(Post post) {
        return postRepository.updatePost(post);
    }*/
}
