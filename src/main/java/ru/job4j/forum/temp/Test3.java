package ru.job4j.forum.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import ru.job4j.forum.service.PostService;

public class Test3 {

    public static void main(String[] args) {
        final Logger LOGGER = LoggerFactory.getLogger(PostService.class);

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = null;
        try {
            session = sf.getCurrentSession();
            session.beginTransaction();

            var rsl = session.createQuery("from User as a where a.username = :Name")
                    .setParameter("Name", "Ivan Sobolev")
                    .getResultList();
            System.out.println(rsl);
            LOGGER.debug("debug logger", rsl);
            session.getTransaction().commit();
            System.out.println("Done!");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
            StandardServiceRegistryBuilder.destroy(registry);
        }
    }
}
