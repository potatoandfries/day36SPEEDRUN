drop database if exists feeds;

create database feeds;

use feeds;

create table posts (
	post_id varchar(8) not null,
    comments mediumtext,
    picture mediumblob,

primary key(post_id)
);

grant all privileges on feeds.* to fred@'%';

flush privileges
    