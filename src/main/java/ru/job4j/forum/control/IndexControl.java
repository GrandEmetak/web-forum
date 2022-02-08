package ru.job4j.forum.control;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.job4j.forum.repository.HbmRepository;
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
 * !!! IMPORTANT
 * private final PostService posts; - поле объекта замено на HbmRepository(Hibernate)
 *     public IndexControl(PostService posts) {
 *         this.posts = posts;
 *     }
 */
@Controller
public class IndexControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexControl.class);

    private static final Marker DEBUG = MarkerFactory.getMarker("DEBUG");

    private final PostService postService;

    public IndexControl(PostService postService) {
        this.postService = postService;
    }

    /**
     * возможно заменить на
     * model.addAttribute("posts", posts.getAll()); через CrudRepository<P,L>
     *
     * @param model
     * @return
     */
    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
       postService.getAll().stream().forEach(System.out::println);
        model.addAttribute("posts", postService.getAll());
        return "index";
    }
}
