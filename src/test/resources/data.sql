DROP TABLE IF EXISTS books;

CREATE TABLE books (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  author VARCHAR(250) NOT NULL
);

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 9 INCREMENT BY 1;

INSERT INTO books (id, author, title) VALUES
  (1, 'Andrzej Pilipiuk', 'Czarownik Iwanow'),
  (2, 'Andrzej Pilipiuk', 'Trucizna'),
  (3, 'Andrzej Pilipiuk', 'Norweski Dziennik'),
  (4, 'Robert Ludlum', 'Tożsamość Bournea'),
  (5, 'Robert Ludlum', 'Krucjata Bournea'),
  (6, 'Robert Ludlum', 'Ultimatum Bournea'),
  (7, 'Andrzej Ziemiański', 'Achaja'),
  (8, 'Andrzej Ziemiański', 'Toy Wars');