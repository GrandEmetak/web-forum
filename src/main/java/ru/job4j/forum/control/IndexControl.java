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

    private static Logger logger = LoggerFactory.getLogger(IndexControl.class);
    private static Marker debug = MarkerFactory.getMarker("DEBUG");

//    private final PostService posts;
//
//    public IndexControl(PostService posts) {
//        this.posts = posts;
//    }

    private final HbmRepository hbmRepository;

    public IndexControl(HbmRepository hbmRepository) {
        this.hbmRepository = hbmRepository;
    }

    @GetMapping({"/", "/index"})
    public String index(Model model) {
        model.addAttribute("user", SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal());
//        model.addAttribute("posts", posts.getAll()); через CrudRepo
        hbmRepository.getAll().stream().forEach(System.out::println);
        model.addAttribute("posts", hbmRepository.getAll());
        logger.info(debug, " getAll() Post List {}");
        logger.debug(debug, " getAll() Post List {}", hbmRepository.getAll());
        return "index";
    }
}
