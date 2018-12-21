CREATE EXTENSION IF NOT EXISTS pgcrypto;

CREATE TABLE IF NOT EXISTS movie (
  id uuid PRIMARY KEY,
  title TEXT,
  RATING INTEGER,
  YEAR INTEGER,
  GENRE TEXT
);

CREATE TABLE IF NOT EXISTS genre_sim (
  genre1 TEXT,
  genre2 TEXT,
  coeff  FLOAT
);

CREATE OR REPLACE FUNCTION cmp_genres(g1 TEXT, g2 TEXT)
RETURNS REAL
AS $$
DECLARE _cft_ REAL;
BEGIN
  SELECT coeff FROM genre_sim
  WHERE genre1 = g1 AND genre2 = g2
  INTO _cft_;

  IF _cft_ IS NULL THEN
    RETURN 0;
  END IF;

  RETURN _cft_;
END;
$$
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION cmp_int(y1 INTEGER, y2 INTEGER)
RETURNS REAL
AS $$
DECLARE _diff_ INTEGER;
DECLARE _cft_ REAL;
BEGIN
  _diff_ := abs(y2 - y1);

  IF _diff_ > 10 THEN
    RETURN 0;
  END IF;

  _cft_ := (10 - _diff_) / 10.0;

  RETURN _cft_;
END;
$$
LANGUAGE PLPGSQL;

CREATE OR REPLACE FUNCTION fuzzy_and(v1 REAL, v2 REAL, v3 REAL)
RETURNS REAL
AS $$
  SELECT least(least(v1, v2), v3);
$$ LANGUAGE SQL;

CREATE OR REPLACE FUNCTION get_top_matches(g TEXT, y INTEGER, r INTEGER)
RETURNS SETOF movie
AS $$
SELECT m.* FROM "movie" m
ORDER BY fuzzy_and(cmp_genres(g,m.genre),cmp_int(y,m.year),cmp_int(r,m.rating))
LIMIT 5;
$$
LANGUAGE SQL;
