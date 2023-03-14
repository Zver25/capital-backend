create table users(
    id bigint primary key auto_increment,
    username varchar(256) not null unique,
    password varchar(1024) not null
);