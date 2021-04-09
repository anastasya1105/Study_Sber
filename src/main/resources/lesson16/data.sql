set schema public;

drop table IF EXISTS FIBONACCI;

create table IF NOT EXISTS FIBONACCI (
    id INT PRIMARY KEY AUTO_INCREMENT,
    number VARCHAR(100)
);
