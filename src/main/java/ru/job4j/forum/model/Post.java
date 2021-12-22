package ru.job4j.forum.model;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 */
public class Post {
    private int id;
    private String name;
    private String description;
    private Calendar created;

    private User user;

    public static Post of(String name, String description) {
        Post post = new Post();
        post.created = new GregorianCalendar();
        post.name = name;
        post.description = description;
        post.created.setTime(post.created.getTime());
        return post;
    }

    public static Post of(int id, String name, String description) {
        Post post = new Post();
        post.created = new GregorianCalendar();
        post.id = id;
        post.name = name;
        post.description = description;
        post.created.setTime(post.created.getTime());
        return post;
    }

    public void addUserToPost(User user) {
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Calendar getCreated() {
        return created;
    }

    public void setCreated(Calendar created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Post post = (Post) o;
        return id == post.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Post{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", description='" + description + '\''
                + ", User='" + user + '\''
                + ", created=" + created
                + '}';
    }
}
