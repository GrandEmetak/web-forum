package ru.job4j.forum.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * подключим к проекту "Автонарушитель" базу данных.
 * На данном этапы мы напишем все через JDBC
 *  В данно классе - В нем нужно создать бин, который будет содержать пул соединений.
 * 0. Spring DataSource [#6878 #239808]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.3. Template, ORM
 * - @PropertySource("classpath:app.properties")  -- эта аннотация говорит Spring считать файл.
 * Далее настройки можно получить через аннотацию @Value("${jdbc.driver}") String driver,
 * Метод ds загружает пул соединений.
 * Метод jdbc создает обертку для работы с базой.
 */
@Configuration
@PropertySource("classpath:app.properties")
@EnableTransactionManagement
public class JdbcConfig {
    @Bean
    public DataSource ds(@Value("${jdbc.driver}") String driver,
                         @Value("${jdbc.url}") String url,
                         @Value("${jdbc.username}") String username,
                         @Value("${jdbc.password}") String password) {
        BasicDataSource ds = new BasicDataSource();
        ds.setDriverClassName(driver);
        ds.setUrl(url);
        ds.setUsername(username);
        ds.setPassword(password);
        return ds;
    }

    @Bean
    public JdbcTemplate jdbc(DataSource ds) {
        return new JdbcTemplate(ds);
    }
}
