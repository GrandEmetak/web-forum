package ru.job4j.forum.model;

import java.util.Objects;

/**
 * Роль авторизации пользователя
 * 0. Spring Boot [#6880]
 * Уровень : 3. МидлКатегория : 3.4. SpringТопик : 3.4.5. Boot
 * В качестве проекта мы сделаем классическое приложение - форум.
 * Создайте модели Post, User.
 * Хранение данных в памяти. Базу данных подключать не надо.
 */
public class Authority {

    private int id;

    private String authority;

    public static Authority of(int id, String authority) {
        Authority auth = new Authority();
        auth.id = id;
        auth.authority = authority;
        return auth;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Authority authority = (Authority) o;
        return id == authority.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Authority{"
                + "id=" + id
                + ", authority='" + authority + '\''
                + '}';
    }
}
