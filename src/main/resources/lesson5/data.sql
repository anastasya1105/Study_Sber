set schema public;

drop table IF EXISTS SERVERDB;

create table IF NOT EXISTS SERVERDB (
    CardNumber INT,
    PIN INT,
    FullName VARCHAR(100),
    Balance INT
);
-- CREATE UNIQUE INDEX IF NOT EXISTS UNIQUE_SERVERDB ON SERVERDB(CardNumber, PIN);