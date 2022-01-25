package ru.job4j.forum.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.PostService;
import ru.job4j.forum.service.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * Контроллер отвечающий за Посты create/update/save
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
@Controller
public class PostControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

    private static final Marker DEBUG = MarkerFactory.getMarker("DEBUG");

    private final PostService postService;
    private final UserService userService;

    public PostControl(PostService posts, UserService userService) {
        this.postService = posts;
        this.userService = userService;
    }

    /**
     * was  model.addAttribute("user", userService.findByNameUser(user));
     * @param user
     * @param model
     * @return
     */
    @GetMapping("/create")
    public String create(@RequestParam("user") String user, Model model) {
        LOGGER.info("@RequestParam(user) String user {}", user);
        LOGGER.debug("MODEL {}", model);
        model.addAttribute("user", userService.findByNameUser(user));
        return "post/create";
    }

    /**
     * was localRepo model.addAttribute("post", postService.findById(id));
     * @param id
     * @param model
     * @return
     */
    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post/update";
    }

    /**
     * +
     *  postService.updatePost(postService.saveData(post));
     * @param post
     * @return
     */
    @PostMapping("/saveUpdate")
    public String saveUpdate(@ModelAttribute Post post) {
               postService.updatePost(post);
        return "redirect:/";
    }

    /**
     * Метод сохраняет новый пост в БД
     * delete local method signature
     * var usr = userService.findById(post.getId()); local repo
     * postService.save(postService.putUserToPost(post, usr));
     * need find user by id
     *
     * @param post новое объявление на сайте
     * @return сервлет индексной страницы с перечнем всех постов
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        var user = userService.findUserById(post.getId());
        postService.save(postService.putUserToPost(post, user));
        return "redirect:/";
    }
}
