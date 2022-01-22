package ru.job4j.forum.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Service;
import ru.job4j.forum.model.Post;

import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.HbmRepository;
import ru.job4j.forum.repository.PostRepositoryLocal;
import ru.job4j.forum.store.PostRepository;

import java.util.ArrayList;
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
 * 2. Spring boot security [#296071]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * - Подключите Spring Security к проекту.
 * - Сделайте сразу интеграцию с базой данных.
 * не активные методы относятся к локальному классу репозиторию
 */
@Service
public class PostService {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    private static final Marker DEBUG = MarkerFactory.getMarker("DEBUG");

    private final PostRepository postsStore;

    private final PostRepositoryLocal postRepositoryLocal;

    private  final HbmRepository hbmRepository;

    public PostService(PostRepository posts,
                       PostRepositoryLocal postRepository,
                       HbmRepository hbmRepository) {
        this.postsStore = posts;
        this.postRepositoryLocal = postRepository;
        this.hbmRepository = hbmRepository;
    }

    public List<Post> getAll() {
        List<Post> rsl = new ArrayList<>();
        postsStore.findAll().forEach(rsl::add);
        // rsl.stream().forEach(System.out::println);
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
//        System.out.println("TO chto prislo findByID " + id);
//        Integer jf = id;
//        var optO = postsStore.findById(jf.longValue());
//        return optO.orElse(null);
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
     * @param post Post Object
     */
    public Post save(Post post) {
     return postsStore.save(post);
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

/* * ***/

/*    public Collection<Post> getAll() {
        return postRepository.getAll();
    }*/

//    /**
//     * find By Id Post Object
//     *
//     * @param id Post Object
//     * @return Post Object
//     */
//    public Post findById(int id) {
//        return postRepository.findByIdPost(id);
//    }

//    public Post putUserToPost(Post post, User user) {
//        Post post1 = Post.of(post.getName(), post.getDescription());
//        post1.setUser(user);
//        return post1;
//    }

//    /**
//     * save Post Object in Repo
//     *
//     * @param post Post Object
//     */
//    public void save(Post post) {
//        postRepository.save(post);
//    }

//    /**
//     * save new data in Post Object
//     *
//     * @param post Post Object
//     * @return Post Object
//     */
//    public Post saveData(Post post) {
//        postRepository.updatePost(post);
//        return post;
//    }

//    /**
//     * update Post object
//     *
//     * @param post Post Object include new info
//     * @return Post Object
//     */
//    public Post updatePost(Post post) {
//        return postRepositoryLocal.updatePost(post);
//    }
}
