INSERT IGNORE INTO author (name)
VALUES ('Stephen King');

INSERT IGNORE INTO author (name)
VALUES ('J. K. Rowling');

INSERT IGNORE INTO book (name, type, volume, generation, author_id)
VALUES ('The Shining', 'paperback', 1, 3, 1);

INSERT IGNORE INTO book (name, type, volume, generation, author_id)
VALUES ('The Gunslinger', 'ebook', 5, 2, 1);

INSERT IGNORE INTO book (name, type, volume, generation, author_id)
VALUES ('Harry Potter and the Cursed Child', 'paperback', 8, 3, 2);