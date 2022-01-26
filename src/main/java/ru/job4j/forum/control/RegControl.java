package ru.job4j.forum.control;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.service.UserService;

/**
 * Класс отвечает за регистрацию пользователя в системе
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 * 2. Регистрация пользователя [#296069 #241520]00
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.4. Security
 * убраны
 * private ServiceReg serviceReg;
 * <p>
 * public RegControl(ServiceReg serviceReg) {
 * this.serviceReg = serviceReg;
 * }
 * - @GetMapping("/reg")
 * public String regPage() {
 * return "reg";
 * }
 * <p>
 * - @PostMapping("/reg")
 * public String regSave(@ModelAttribute User user) {
 * serviceReg.regNewUser(user);
 * return "redirect:/login";
 * }
 * заменены на - >
 */
@Controller
public class RegControl {
    @Lazy
    private final PasswordEncoder encoder;
    private final UserService userService;
    private final AuthorityRepository authorities;

    public RegControl(@Lazy PasswordEncoder encoder,
                      UserService userService,
                      AuthorityRepository authorities) {
        this.encoder = encoder;
        this.userService = userService;
        this.authorities = authorities;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    /**
     * проверяет есть ли пользователь с таким именем,
     * если нет то регестрация прозходит успешно
     * если есть возвразает с предупреждением к форме регистрации
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
        user.setAuthority(authorities.findByAuthority("USER"));
        userService.saveUser(user);
        return "redirect:/login";
    }
}

