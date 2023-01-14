package web.forum.controller;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import web.forum.model.User;
import web.forum.service.AuthorityService;
import web.forum.service.UserService;

/**
 * Класс отвечает за регистрацию пользователя в системе
 */
@Controller
public class RegControl {

    @Lazy
    private final PasswordEncoder encoder;
    private final UserService userService;

    public RegControl(@Lazy PasswordEncoder encoder,
                      UserService userService,
                      AuthorityService authorityService) {
        this.encoder = encoder;
        this.userService = userService;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    /**
     * проверяет есть ли пользователь с таким именем,
     * если нет то регистрация происходит успешно
     * если есть возвращает с предупреждением к форме регистрации
     *
     * @param user  Object User(username, password)
     * @param model (String - warning)
     * @return reg.jsp page or login.jsp page
     */
    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user, Model model) {

        var userUnique = userService.findUserByUsername(user.getUsername());
        if (userUnique != null) {
            String errorMessage = null;
            errorMessage = "A user with the same name already exists,\n"
                    + " username must be unique !!!";
            model.addAttribute("errorMessage", errorMessage);
            return "/reg";
        }
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        var rsl = userService.saveUser(user);
        System.out.println("USER _> " + rsl);
        return "redirect:/login";
    }
}

