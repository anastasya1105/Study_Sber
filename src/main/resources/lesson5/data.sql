set schema public;

create table IF NOT EXISTS SERVERDB (
    CardNumber INT,
    PIN INT,
    FullName VARCHAR(100),
    Balance INT
);
