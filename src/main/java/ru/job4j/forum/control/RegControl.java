package ru.job4j.forum.control;

import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.User;
import ru.job4j.forum.repository.AuthorityRepository;
import ru.job4j.forum.repository.UserRepository;
/* import ru.job4j.accident.repository.AuthorityRepository;*/
/* import ru.job4j.accident.repository.UserRepository;*/

/**
 * Класс отвечает за регистрацию пользователя в системе
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 * 2. Регистрация пользователя [#296069 #241520]00
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.4. Security
 *  убраны
 *  private ServiceReg serviceReg;
 *
 *     public RegControl(ServiceReg serviceReg) {
 *         this.serviceReg = serviceReg;
 *     }
 *     - @GetMapping("/reg")
 *     public String regPage() {
 *         return "reg";
 *     }
 *
 *    - @PostMapping("/reg")
 *     public String regSave(@ModelAttribute User user) {
 *         serviceReg.regNewUser(user);
 *         return "redirect:/login";
 *     }
 *    заменены на - >
 */
@Controller
public class RegControl {

    private final PasswordEncoder encoder;
    private final UserRepository users;
    private final AuthorityRepository authorities;

    public RegControl(PasswordEncoder encoder, UserRepository users, AuthorityRepository authorities) {
        this.encoder = encoder;
        this.users = users;
        this.authorities = authorities;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        user.setEnabled(true);
        user.setPassword(encoder.encode(user.getPassword()));
        user.setAuthority(authorities.findByAuthority("USER"));
        users.save(user);
        return "redirect:/login";
    }
}

