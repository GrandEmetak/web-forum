create table authoritys
(
    id        serial primary key,
    authority varchar(255)
);

create table users
(
    id           serial primary key,
    enabled      boolean,
    password     varchar(255),
    username     varchar(255),
    authority_id int references authoritys (id)
);

create table posts
(
    id          serial primary key,
    name        varchar(2000),
    description text,
    created     timestamp without time zone not null default now(),
    user_id     int references users (id)
);

create table discussion
(
    id          serial primary key,
    description text,
    created     timestamp without time zone not null default now(),
    user_id     int references users (id),
    post_id     int references posts (id)
);

