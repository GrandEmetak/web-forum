create table authoritys
(
    id        serial primary key,
    authority varchar(255)
);

insert into authoritys (authority)
values ('ROLE_USER');
insert into authoritys (authority)
values ('ROLE_ADMIN');
create table users
(
    id           serial primary key,
    enabled      boolean,
    password     varchar(255),
    username     varchar(255),
    authority_id int references authoritys (id)
);

insert into users (username, enabled, password, authority_id)
values ('Ivan Sobolev', true, '$2a$10$TqfYcEWuXxoY6L/aAz2X8egz47vmbtzqAH3PrnWF2gq2vfo7hmbHW',
        (select id from authoritys where authority = 'USER'));
insert into users (username, enabled, password, authority_id)
values ('Svetlana Donovan', true, '$2a$10$HiWxVMtZjCOHyk4ktTzeQee7TR/BBrpnEmtORIi32N6Fh6NBiFpA.',
        (select id from authoritys where authority = 'USER'));
insert into users (username, enabled, password, authority_id)
values ('Sergei Shirokov', true, '$2a$10$CzztUARq9leM95pmfI70OOT.qOrmxqQF.idwN1AOmpKRAMyzkjJsm',
        (select id from authoritys where authority = 'USER'));
insert into users (username, enabled, password, authority_id)
values ('root', true, '$2a$10$NLzdxS.HApEfFa9M1P6sN.qbqDE3trwQnJzkjYBGmGjdqIMu9/dbi',
        (select id from authoritys where authority = 'ADMIN'));