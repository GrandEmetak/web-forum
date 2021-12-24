package ru.job4j.forum.control;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import ru.job4j.forum.Main;
import static org.junit.Assert.*;
/**
 * пример тестирования Login страницы.
 * public final class MockMvc
 * https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/MockMvc.html
 * 0. Spring test [#6881]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.6. Tests
 * добавить зависимости в pom.xml
 * + Для тестирования активно используются объекты-заглушки.
 * -@SpringBootTest(classes = Main.class)
 * -@AutoConfigureMockMvc
 * Создает контекст.
 * <p>
 * -@Autowired
 * private MockMvc mockMvc;
 * Создает объект-заглушку. Мы можем отправлять в него запросы.
 * <p>
 * - @WithMockUser (указывается в случае проверки работы будучи авторизованным)
 * Подставляет авторизованного пользователя в запрос. - указывать после аннотации @Test
 */
@SpringBootTest(classes = Main.class)
@AutoConfigureMockMvc
public class LoginControlTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldReturnDefaultMessage() throws Exception {
        this.mockMvc.perform(get("/login"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("login"));
    }
}