package web.forum.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import web.forum.service.PostService;

@Controller
public class IndexControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexControl.class);

    private static final Marker DEBUG = MarkerFactory.getMarker("DEBUG");

    private final PostService postService;

    public IndexControl(PostService postService) {
        this.postService = postService;
    }


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
