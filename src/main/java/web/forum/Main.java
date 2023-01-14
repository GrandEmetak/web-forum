package web.forum;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

/**
 * Main class SpringBoot Start
 * <p>
 * Подключаем к проекту Liquibase -
 * Liquibase - независимая от базы данных библиотека для отслеживания,
 * управления и применения изменений схемы базы данных.
 * + зависимости в pom.xml
 * 0. Spring Liquibase [#302330]02
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.7. Deploy
 * Liquibase будет запускаться каждый раз при старте приложения. *
 * Этого эффекта мы добьемся через Spring.
 * <p>
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

    /**
     * Подключаем к проекту Liquibase -
     * Liquibase - независимая от базы данных библиотека для отслеживания,
     * управления и применения изменений схемы базы данных.
     *
     * @param ds
     * @return
     */
    @Bean
    public SpringLiquibase liquibase(DataSource ds) {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:liquibase-changeLog.xml");
        liquibase.setDataSource(ds);
        return liquibase;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
