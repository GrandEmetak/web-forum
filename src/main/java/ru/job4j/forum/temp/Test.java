package ru.job4j.forum.temp;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * данный класс является тестовым
 */
public class Test {
    public static void main(String[] args) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(calendar.getTime());
        System.out.println(" то что в календаре : " + calendar);
        System.out.println(calendar.getTime());
        Timestamp timestamp = Timestamp.valueOf(LocalDateTime.now());
        System.out.println(timestamp);

  /*      System.out.println(LocalDateTime.now());
        User user = new User();
        user.setUsername("Ivan Sobolev");
        Post post = Post.of(1, "Продам веллоспед", "Продам велосипедб новый горный");
        post.addUserToPost(user);
        System.out.println(post);*/
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123456");
        System.out.println(pwd);

     /*   PostRepository postRepository = new PostRepository();
        PostService postService = new PostService(postRepository);
        var col = postRepository.getAll();
        col.stream().forEach(System.out::println);
        var cl = postService.findById(1);
        System.out.println(cl);
        cl.setName("Sebastian Pereiro");
        cl.setDescription("Торговец черным деревом");
        var rsl = postService.updatePost(cl);
        System.out.println(rsl);*/
    }
}
