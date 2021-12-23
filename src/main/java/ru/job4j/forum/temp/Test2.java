package ru.job4j.forum.temp;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ru.job4j.forum.model.Authority;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

public class Test2 {

    public static void main(String[] args) {
        Authority authority = Authority.of("USER");
        Authority adm = Authority.of("ADMIN");

        User user = User.of("$2a$10$7mu7nQOH2V675LSIVeh..ODxMFM8sID7f2jMRwdWVrLfgpZS/zqmG",
                "user", true);
        User ivan = User.of("$2a$10$7mu7nQOH2V675LSIVeh..ODxMFM8sID7f2jMRwdWVrLfgpZS/zqmG",
                "Ivan Sobolev", true);
        user.setAuthority(authority);
        ivan.setAuthority(authority);

        Post post1 = Post.of("Продам велосипед",
                "Продам велосипед, новый, горный, 25 скоростей Schimano");
        Post post2 = Post.of("Куплю мотоцикл",
                "Куплю или приму в дар мотоцикл, б/у, находу");
        user.addPostToUser(post1);
        ivan.addPostToUser(post2);
        post1.addUserToPost(user);
        post2.addUserToPost(ivan);

        final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
                .configure("hibernate.cfg.xml").build();
        SessionFactory sf = new MetadataSources(registry).buildMetadata().buildSessionFactory();

        Session session = null;
        try {
            session = sf.getCurrentSession();
            session.beginTransaction();

            System.out.println("before save : " + user);
            System.out.println("before save : " + post1);

            session.persist(post1);
            session.persist(post2);
            var acc = session.get(User.class, 1);

            System.out.println("after save User : " + acc);
            var p1 = session.get(Post.class, 1);
            System.out.println("Пост после добавления в БД : " + p1);
            System.out.println("Принт ПОСТОВ у Юзере : " + p1.getUser().getPostSet());
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

