package ru.job4j.forum.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.job4j.forum.model.Post;
import ru.job4j.forum.model.User;

import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Test {
    public static void main(String[] args) {
//        Calendar calendar = new GregorianCalendar();
//        calendar.setTime(calendar.getTime());
//        System.out.println(calendar.getTime());
//
//        System.out.println(LocalDateTime.now());
//        User user = new User();
//        user.setUsername("Ivan Sobolev");
//        Post post = Post.of(1, "Продам веллоспед", "Продам велосипедб новый горный");
//        post.addUserToPost(user);
//        System.out.println(post);
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String pwd = encoder.encode("123456");
        System.out.println(pwd);
    }
}
