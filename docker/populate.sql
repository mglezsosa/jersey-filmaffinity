USE `mglezsosa-filmaffinity`;


INSERT INTO Director
(
    fullname
)
VALUES
(
    'Christopher Nolan'
),
(
    'Martin Scorsese'
);


INSERT INTO Movie
(
    title, genre, year, Director_id
)
VALUES
(
    'Interstellar', 'SciFi', 2014, 1
),
(
    'The Dark Knight Rises', 'Thriller', 2012, 1
),
(
    'The Wolf of Wall Street', 'Comedy', 2013, 2
),
(
    'Shutter Island', 'Thriller', 2010, 2
),
(
    'Inception', 'SciFi', 2010, 1
);


INSERT INTO Actor
(
    fullname
)
VALUES
(
    'Christian Bale'
),
(
    'Anne Hathaway'
),
(
    'Matthew McConaughey'
),
(
    'Rutger Hauer'
),
(
    'Leonardo DiCaprio'
),
(
    'Mark Ruffalo'
),
(
    'Joseph Gordon-Levitt'
),
(
    'Michael Caine'
);


INSERT INTO Movie_has_Actor
(
    Movie_id, Actor_id
)
VALUES
(
    1, 2
),
(
    1, 3
),
(
    1, 8
),
(
    2, 1
),
(
    2, 2
),
(
    2, 7
),
(
    2, 8
),
(
    3, 3
),
(
    3, 5
),
(
    4, 5
),
(
    4, 6
),
(
    5, 5
),
(
    5, 7
),
(
    5, 8
);
