insert into authoritys (authority)
values ('ROLE_USER');
insert into authoritys (authority)
values ('ROLE_ADMIN');

insert into users (username, enabled, password, authority_id)
values ('Ivan Sobolev', true, '$2a$10$TqfYcEWuXxoY6L/aAz2X8egz47vmbtzqAH3PrnWF2gq2vfo7hmbHW', 1);

insert into users (username, enabled, password, authority_id)
values ('Svetlana Donovan', true, '$2a$10$HiWxVMtZjCOHyk4ktTzeQee7TR/BBrpnEmtORIi32N6Fh6NBiFpA.', 1);

insert into users (username, enabled, password, authority_id)
values ('Sergei Shirokov', true, '$2a$10$CzztUARq9leM95pmfI70OOT.qOrmxqQF.idwN1AOmpKRAMyzkjJsm', 1);

insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$NLzdxS.HApEfFa9M1P6sN.qbqDE3trwQnJzkjYBGmGjdqIMu9/dbi', 2);

insert into posts (description, name, user_id)
values ('Продам велосипед, новый, горный, 25 скоростей Schimano',
        'Продам велосипед', 1);
insert into posts (description, name, user_id)
values ('Куплю или приму в дар мотоцикл, б/у, находу',
        'Куплю мотоцикл', 2);
insert into posts (description, name, user_id)
values ('Требования Java, JS, HTML, PostgreSQL, Git, Linux',
        'Вакансия на стажера', 3);

insert into discussion (description, user_id, post_id)
values ('Актуально ли сегодня устроиться на удаленную вакансию?', 1, 3);
insert into discussion (description, user_id, post_id)
values ('Автор ЖЖетт!!! )))', 2, 3);
insert into discussion (description, user_id, post_id)
values ('Все свое ношу с собой)))', 1, 3);
insert into discussion (description, user_id, post_id)
values ('Если вы будете соотвествовать требовниям, то почему нет))', 3, 3);
