-- BookMyShow sample catalog for MySQL
-- Run after the application has created the schema once, or keep spring.jpa.hibernate.ddl-auto=update.
-- Safe to run repeatedly: movies, theatres and shows are checked by their business fields.

CREATE DATABASE IF NOT EXISTS bookmyshow;
USE bookmyshow;

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT 'Dune: Part Two', 'English', 'Sci-Fi / Adventure', 166, 'UA',
       'Paul Atreides unites with Chani and the Fremen while seeking revenge against the conspirators who destroyed his family.',
       'https://images.unsplash.com/photo-1534447677768-be436bb09401?w=900',
       'https://www.youtube.com/watch?v=Way9Dexny3w', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Dune: Part Two');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT 'Monkey Man', 'English', 'Action / Thriller', 113, 'A',
       'An anonymous young man unleashes a campaign of vengeance against the corrupt leaders who murdered his mother.',
       'https://images.unsplash.com/photo-1489599849927-2ee91cede3ba?w=900',
       'https://www.youtube.com/watch?v=g8zxiB5Qhsc', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Monkey Man');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT 'Kung Fu Panda 4', 'English', 'Animation / Comedy', 94, 'U',
       'Po must train a new warrior while facing a powerful shape-shifting chameleon.',
       'https://images.unsplash.com/photo-1517604931442-7e0c8ed2963c?w=900',
       'https://www.youtube.com/watch?v=_inKs4eeHiI', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Kung Fu Panda 4');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT 'Jawan', 'Hindi', 'Action / Thriller', 169, 'UA',
       'A high-octane action drama about a man driven by a personal vendetta to correct the wrongs in society.',
       'https://images.unsplash.com/photo-1574267432553-4b4628081c31?w=900',
       'https://www.youtube.com/watch?v=MWOlnZSnXJo', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Jawan');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT 'Stree 2: Sarkate Ka Aatank', 'Hindi', 'Horror / Comedy', 149, 'UA',
       'The residents of Chanderi return to face a new supernatural threat in this crowd-pleasing horror comedy.',
       'https://images.unsplash.com/photo-1509248961158-e54f6934749c?w=900',
       'https://www.youtube.com/watch?v=KVnheXywIbY', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Stree 2: Sarkate Ka Aatank');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT '12th Fail', 'Hindi', 'Drama', 147, 'U',
       'A determined student overcomes setbacks and finds the courage to pursue a dream bigger than his circumstances.',
       'https://images.unsplash.com/photo-1524995997946-a1c2e315a42f?w=900',
       'https://www.youtube.com/watch?v=WeMJo701muE', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = '12th Fail');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT 'Animal', 'Hindi', 'Action / Crime', 201, 'A',
       'A troubled heir returns home and enters a violent conflict driven by family loyalty and revenge.',
       'https://images.unsplash.com/photo-1485846234645-a62644f84728?w=900',
       'https://www.youtube.com/watch?v=HimcH3lH4cE', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Animal');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT 'Kalki 2898 AD', 'Hindi', 'Sci-Fi / Action', 181, 'UA',
       'In a future shaped by myth and technology, unlikely heroes rise to protect the hope of a new world.',
       'https://images.unsplash.com/photo-1518709268805-4e9042af9f23?w=900',
       'https://www.youtube.com/watch?v=kQDd1AhGIHk', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Kalki 2898 AD');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT '3 Idiots', 'Hindi', 'Comedy / Drama', 170, 'U',
       'Three college friends navigate friendship, pressure, ambition and the meaning of success.',
       'https://images.unsplash.com/photo-1523240795612-9a054b0db644?w=900',
       'https://www.youtube.com/watch?v=K0eDlFX9GMc', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = '3 Idiots');

INSERT INTO movies
(title, language, genre, duration_minutes, certificate, description, poster_url, trailer_url, active)
SELECT 'Interstellar', 'English', 'Sci-Fi / Adventure', 169, 'UA',
       'Explorers travel beyond the limits of space to secure a future for humanity.',
       'https://images.unsplash.com/photo-1446776811953-b23d57bd21aa?w=900',
       'https://www.youtube.com/watch?v=zSWdZVtXT7E', 1
    WHERE NOT EXISTS (SELECT 1 FROM movies WHERE title = 'Interstellar');

INSERT INTO theatres (name, city, address)
SELECT 'INOX: Megaplex', 'Bengaluru', 'Phoenix Marketcity, Mahadevapura'
    WHERE NOT EXISTS (SELECT 1 FROM theatres WHERE name = 'INOX: Megaplex' AND city = 'Bengaluru');

INSERT INTO theatres (name, city, address)
SELECT 'PVR: Orion Mall', 'Bengaluru', 'Brigade Gateway, Malleshwaram'
    WHERE NOT EXISTS (SELECT 1 FROM theatres WHERE name = 'PVR: Orion Mall' AND city = 'Bengaluru');

INSERT INTO theatres (name, city, address)
SELECT 'PVR: Select Citywalk', 'Delhi', 'Saket, New Delhi'
    WHERE NOT EXISTS (SELECT 1 FROM theatres WHERE name = 'PVR: Select Citywalk' AND city = 'Delhi');

INSERT INTO theatres (name, city, address)
SELECT 'INOX: R-City', 'Mumbai', 'Ghatkopar West, Mumbai'
    WHERE NOT EXISTS (SELECT 1 FROM theatres WHERE name = 'INOX: R-City' AND city = 'Mumbai');

SET @tomorrow = DATE_ADD(CURRENT_DATE, INTERVAL 1 DAY);

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '18:30:00'), TIMESTAMP(@tomorrow, '21:16:00'), 280.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Dune: Part Two' AND t.name = 'INOX: Megaplex' AND t.city = 'Bengaluru'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '18:30:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '21:00:00'), TIMESTAMP(@tomorrow, '23:46:00'), 320.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Dune: Part Two' AND t.name = 'PVR: Orion Mall' AND t.city = 'Bengaluru'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '21:00:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '19:00:00'), TIMESTAMP(@tomorrow, '20:53:00'), 240.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Monkey Man' AND t.name = 'INOX: Megaplex' AND t.city = 'Bengaluru'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '19:00:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '11:00:00'), TIMESTAMP(@tomorrow, '12:34:00'), 180.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Kung Fu Panda 4' AND t.name = 'PVR: Orion Mall' AND t.city = 'Bengaluru'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '11:00:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '20:00:00'), TIMESTAMP(@tomorrow, '22:49:00'), 260.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Jawan' AND t.name = 'INOX: Megaplex' AND t.city = 'Bengaluru'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '20:00:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '18:00:00'), TIMESTAMP(@tomorrow, '20:29:00'), 220.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Stree 2: Sarkate Ka Aatank' AND t.name = 'PVR: Orion Mall' AND t.city = 'Bengaluru'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '18:00:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '19:00:00'), TIMESTAMP(@tomorrow, '21:49:00'), 280.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Jawan' AND t.name = 'PVR: Select Citywalk' AND t.city = 'Delhi'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '19:00:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '22:00:00'), TIMESTAMP(@tomorrow, '00:46:00') + INTERVAL 1 DAY, 300.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Dune: Part Two' AND t.name = 'PVR: Select Citywalk' AND t.city = 'Delhi'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '22:00:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '18:30:00'), TIMESTAMP(@tomorrow, '20:59:00'), 240.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Stree 2: Sarkate Ka Aatank' AND t.name = 'INOX: R-City' AND t.city = 'Mumbai'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '18:30:00')
    );

INSERT INTO shows
(movie_id, theatre_id, starts_at, ends_at, ticket_price, total_seats, available_seats, active, version)
SELECT m.id, t.id, TIMESTAMP(@tomorrow, '21:00:00'), TIMESTAMP(@tomorrow, '23:49:00'), 290.00, 20, 20, 1, 0
FROM movies m CROSS JOIN theatres t
WHERE m.title = 'Jawan' AND t.name = 'INOX: R-City' AND t.city = 'Mumbai'
  AND NOT EXISTS (
    SELECT 1 FROM shows s
    WHERE s.movie_id = m.id AND s.theatre_id = t.id AND s.starts_at = TIMESTAMP(@tomorrow, '21:00:00')
    );

-- Create A1-A10 and B1-B10 for every sample show.
INSERT INTO show_seats (show_id, seat_label, reserved)
SELECT s.id, seats.seat_label, 0
FROM shows s
         JOIN (
    SELECT 'A1' seat_label UNION ALL SELECT 'A2' UNION ALL SELECT 'A3' UNION ALL SELECT 'A4' UNION ALL SELECT 'A5'
    UNION ALL SELECT 'A6' UNION ALL SELECT 'A7' UNION ALL SELECT 'A8' UNION ALL SELECT 'A9' UNION ALL SELECT 'A10'
    UNION ALL SELECT 'B1' UNION ALL SELECT 'B2' UNION ALL SELECT 'B3' UNION ALL SELECT 'B4' UNION ALL SELECT 'B5'
    UNION ALL SELECT 'B6' UNION ALL SELECT 'B7' UNION ALL SELECT 'B8' UNION ALL SELECT 'B9' UNION ALL SELECT 'B10'
) seats
WHERE s.starts_at = TIMESTAMP(@tomorrow, TIME_FORMAT(s.starts_at, '%H:%i:%s'))
  AND NOT EXISTS (
    SELECT 1 FROM show_seats existing
    WHERE existing.show_id = s.id AND existing.seat_label = seats.seat_label
    );

SELECT id, title, language, genre FROM movies ORDER BY id;
SELECT id, movie_id, theatre_id, starts_at, ticket_price FROM shows ORDER BY starts_at;