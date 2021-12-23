package ru.job4j.forum;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

/**
 * подключим авторизацию к проекту
 * Spring использует тот же подход, что и Servlet. Внутри используется Filter,
 * который проверяет входящие запросы и отдает jsessionId.
 * Создадим отдельный класс, в котором сделаем настройки для авторизации.
 * пользователи будут храниться в памяти.
 2. Spring boot security [#296071]
 Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 */
/*@Configuration
@EnableWebSecurity*/
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private DataSource ds;

    /**
     *запросы авторизации и аутентификации.
     * также убраны типовые данные прописаннные для примера и дающие возможность входа user и admin
     *     auth.inMemoryAuthentication()
     *                 .passwordEncoder(passwordEncoder)
     *                 .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
     *                 .and()
     *                 .withUser("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication().dataSource(ds)
                .usersByUsernameQuery("select username, password, enabled "
                        + "from users "
                        + "where username = ?")
                .authoritiesByUsernameQuery(
                        " select u.username, a.authority "
                                + "from authoritys as a, users as u "
                                + "where u.username = ? and u.authority_id = a.id");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * У каждого пользователя есть роль. По роли мы определяем, что пользователь может делать .
     *
     * Метод configure(http) содержит описание доступов и конфигурирование страницы входа в приложение.
     * - ссылки, которые доступны всем.
     * .antMatchers("/login") доступ в SpringSecurity.-.antMatchers("/login", "/reg")
     * .permitAll()
     * - ссылки доступны только пользователем с ролями ADMIN, USER.
     * .antMatchers("/**")
     * .hasAnyRole("ADMIN", "USER")
     * Настройка формы авторизации.
     * .formLogin()
     * .loginPage("/login")
     * .defaultSuccessUrl("/")
     * .failureUrl("/login?error=true")
     * .permitAll()
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/login", "/reg")
                .permitAll()
                .antMatchers("/**")
                .hasAnyRole("ADMIN", "USER")
                .and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/")
                .failureUrl("/login?error=true")
                .permitAll()
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout=true")
                .invalidateHttpSession(true)
                .permitAll()
                .and()
                .csrf()
                .disable();
    }
}
