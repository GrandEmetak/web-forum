package ru.job4j.forum.control;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.job4j.forum.service.PostService;

/**
 * Controller index page - main page
 * 1. Spring boot repository [#2095]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * private final PostService posts - произведена замена,
 * с локального репозитория класс ru.job4j.forum.repository.PostRepository, на
 * interface PostRepository -ru.job4j.forum.store -
 * ! Service  больше не работает с лок.репозиторием,
 * а работает с репозит потдерживающим БД (Postgres)
 */
@Controller
public class IndexControl {

    private final PostService posts;

    public IndexControl(PostService posts) {
        this.posts = posts;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
        model.addAttribute("posts", posts.getAll());
        return "index";
    }
}
