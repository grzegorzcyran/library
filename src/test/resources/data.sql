DROP TABLE IF EXISTS books;

CREATE TABLE books (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  title VARCHAR(250) NOT NULL,
  author VARCHAR(250) NOT NULL
);

INSERT INTO books (author, title) VALUES
  ('Andrzej Pilipiuk', 'Czarownik Iwanow'),
  ('Andrzej Pilipiuk', 'Trucizna'),
  ('Andrzej Pilipiuk', 'Norweski Dziennik'),
  ('Robert Ludlum', 'Tożsamość Bournea'),
  ('Robert Ludlum', 'Krucjata Bournea'),
  ('Robert Ludlum', 'Ultimatum Bournea'),
  ('Andrzej Ziemiański', 'Achaja'),
  ('Andrzej Ziemiański', 'Toy Wars');