package ru.job4j.forum.control;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;
import ru.job4j.forum.service.ServiceReg;
/* import ru.job4j.accident.repository.AuthorityRepository;*/
/* import ru.job4j.accident.repository.UserRepository;*/

/**
 * Класс отвечает за регистрацию пользователя в системе
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 */
@Controller
public class RegControl {

    private ServiceReg serviceReg;

    public RegControl(ServiceReg serviceReg) {
        this.serviceReg = serviceReg;
    }

    @GetMapping("/reg")
    public String regPage() {
        return "reg";
    }

    @PostMapping("/reg")
    public String regSave(@ModelAttribute User user) {
        serviceReg.regNewUser(user);
        return "redirect:/login";
    }
}

