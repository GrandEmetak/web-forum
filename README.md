# job4j_forum

[![Build Status](https://app.travis-ci.com/SlartiBartFast-art/job4j_forum.svg?branch=main)](https://app.travis-ci.com/SlartiBartFast-art/job4j_forum)

 Классическое приложение - форум.

Used technologies
______________________________________________
- Java Core
- JWT
- Spring Boot Security
- Spring Boot Data JPA
- Spring Boot Web
- Spring Boot Tomcat
- Maven
- Travis C.I.
- Heroku

Это приложение "форум " запущено в облаке. Для этого мной был использовать сервис Hekoru.
Ознакомиться с приложением вы можете перейдя по ссылке:

https://stark-plateau-02521.herokuapp.com/login

______________________________________________

1. Регистрация на форуме. reg

Сервисом могут пользоваться разные пользователи.
Каждый пользователь при регистрации указывает уникальный логин/username и пароль.

в случае если пользователь регистрируется впервые
![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_1.jpg)

Ответ от сервера, в случае если такой пользователь уже существует

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_2.jpg)

2. Авторизация. login

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_3.jpg)


![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_4.jpg)

 - в случае не верного логина/пароля пользователь получит предупреждение

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_9.jpg)

3. Главная страница форума.

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_5.jpg)

доступны:

   - просмотр текущих тем с авторами

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_5.jpg)

   - создать свою тему для общения(став автором темы)

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_10.jpg)

   - редактирование заголовка темы, если зарегистрированный пользователь является автором данной темы форума 

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_11.jpg)

   - участие в чате выбранной темы

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_6.jpg)

   - написать сообщение 

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_7.jpg)


4. Выход с сайта форума и выход из логина.

![Image of Arch](https://github.com/SlartiBartFast-art/job4j_forum/blob/main/image/Screenshot_8.jpg)

