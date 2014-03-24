

DROP TABLE book IF EXISTS;

CREATE TABLE book  (
    id BIGINT IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(50),
    price BIGINT(50)
);
INSERT INTO book values (1,'amin',100);