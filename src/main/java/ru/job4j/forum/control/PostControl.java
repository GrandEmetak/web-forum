package ru.job4j.forum.control;

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
/*@Controller*/
public class PostControl {

    private final PostService postService;
    private final UserService userService;

    public PostControl(PostService posts, UserService userService) {
        this.postService = posts;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String create(@RequestParam("user") String user, Model model) {
        model.addAttribute("user", userService.findByNameUser(user));
        return "post/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
/*        model.addAttribute("post", postService.findById(id));*/
        return "post/update";
    }

    @PostMapping("/saveUpdate")
    public String saveUpdate(@ModelAttribute Post post) {
/*        postService.updatePost(postService.saveData(post));*/
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        var usr = userService.findById(post.getId());
/*        postService.save(postService.putUserToPost(post, usr));*/
        return "redirect:/";
    }
}
