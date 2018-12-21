INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie1','thriller',2005, 7);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie2','drama',2001,9);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie3','horror',2008,5);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie4','thriller',2003,7);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie5','horror',2007,7);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie6','drama',2003,10);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie7','comedy',2005,4);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie8','fantasy',2007,9);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie9','fantasy',2004,10);
INSERT INTO movie(id,title,genre,year,rating)
  VALUES(gen_random_uuid(),'movie10','comedy',2001,9);

SELECT * FROM movie;

INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('horror','horror',1);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('horror','thriller',0.8);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('horror','comedy',0.1);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('horror','fantasy',0.3);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('horror','drama',0.4);

INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('thriller','horror',0.8);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('thriller','thriller',1);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('thriller','comedy',0.2);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('thriller','fantasy',0.3);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('thriller','drama',0.3);

INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('comedy','horror',0.2);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('comedy','thriller',0.3);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('comedy','comedy',0.1);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('comedy','fantasy',0.5);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('comedy','drama',0.4);

INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('fantasy','horror',0.4);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('fantasy','thriller',0.3);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('fantasy','comedy',0.4);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('fantasy','fantasy',1);
INSERT INTO genre_sim(genre1,genre2,coeff)
  VALUES('fantasy','drama',0.3);

SELECT * FROM genre_sim;
