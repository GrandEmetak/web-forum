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

@Controller
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
        System.out.println("update" + id);
        model.addAttribute("post", postService.findById(id));
        return "post/update";
    }

    @PostMapping("/saveUpdate")//(@RequestParam("id") int id,
    public String saveUpdate(@ModelAttribute Post post) {
//        Post post1 = postService.findById(id);
        System.out.println(post);

        postService.updatePost(postService.saveData(post));
        return "redirect:/";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Post post) {
        var usr = userService.findById(post.getId());
        postService.save(postService.putUserToPost(post, usr));
        return "redirect:/";
    }
}
