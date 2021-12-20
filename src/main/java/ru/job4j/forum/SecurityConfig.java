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

/**
 * подключим авторизацию к проекту
 * Spring использует тот же подход, что и Servlet. Внутри используется Filter,
 * который проверяет входящие запросы и отдает jsessionId.
 * Создадим отдельный класс, в котором сделаем настройки для авторизации.
 * пользователи будут храниться в памяти.
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .passwordEncoder(passwordEncoder)
                .withUser("user").password(passwordEncoder.encode("123456")).roles("USER")
                .and()
                .withUser("admin").password(passwordEncoder.encode("123456")).roles("USER", "ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * - ссылки, которые доступны всем.
     * .antMatchers("/login")
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
                .antMatchers("/login")
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
