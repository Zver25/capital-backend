create table users(
    id bigserial primary key,
    username varchar(256) not null unique,
    password varchar(1024) not null
);