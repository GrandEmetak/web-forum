package web.forum.controller;

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
import web.forum.model.Post;
import web.forum.service.PostService;
import web.forum.service.UserService;

/**
 * Контроллер отвечающий за Посты create/update/save
 */
@Controller
public class PostControl {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostControl.class);

    private static final Marker DEBUG = MarkerFactory.getMarker("DEBUG");

    private final PostService postService;
    private final UserService userService;

    public PostControl(PostService posts, UserService userService) {
        this.postService = posts;
        this.userService = userService;
    }

    @GetMapping("/create")
    public String create(@RequestParam("user") String user, Model model) {
        LOGGER.info("@RequestParam(user) String user {}", user);
        LOGGER.debug("MODEL {}", model);
        model.addAttribute("user", userService.findByNameUser(user));
        return "post/create";
    }

    @GetMapping("/update")
    public String update(@RequestParam("id") int id, Model model) {
        model.addAttribute("post", postService.findById(id));
        return "post/update";
    }

    @PostMapping("/saveUpdate")
    public String saveUpdate(@ModelAttribute Post post, Model model) {
        model.addAttribute("post", postService.updatePost(post));
        return "redirect:/";
    }

    /**
     * Метод сохраняет новый пост в БД
     *
     * @param post новое объявление на сайте
     * @return сервлет индексной страницы с перечнем всех постов
     */
    @PostMapping("/save")
    public String save(@ModelAttribute Post post, Model model) {
        var user = userService.findUserById(post.getId());
        model.addAttribute("post", postService.save(postService.putUserToPost(post, user)));
        return "redirect:/";
    }
}
