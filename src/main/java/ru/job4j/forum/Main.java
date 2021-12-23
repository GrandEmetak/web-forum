package ru.job4j.forum;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Main class SpringBoot Start
 * Многие разработчики Spring Boot всегда аннотируют свой основной класс с помощью @Configuration,
 * -@EnableAutoConfigurationи @ComponentScan. Поскольку эти аннотации так часто используются вместе
 * (особенно если вы следуете приведенным выше рекомендациям), Spring Boot предоставляет
 * удобную @SpringBootApplicationальтернативу. *
 * -@SpringBootApplicationАннотация эквивалентно использования @Configuration,
 * -@EnableAutoConfigurationи @ComponentScanс их атрибутами по умолчанию
 */
@SpringBootApplication
public class Main extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(Main.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
