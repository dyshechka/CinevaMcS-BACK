insert into film_mcs.age_restriction (name) values ('16+');
insert into film_mcs.age_restriction (name) values ('18+');

insert into film_mcs.country (name) values ('Россия');
insert into film_mcs.country (name) values ('США');
insert into film_mcs.country (name) values ('Великобритания');
insert into film_mcs.country (name) values ('Канада');

insert into film_mcs.film_format (name) values ('2D');
insert into film_mcs.film_format (name) values ('3D');

insert into film_mcs.genre (name) values ('Триллер');
insert into film_mcs.genre (name) values ('Комедия');
insert into film_mcs.genre (name) values ('Драма');

insert into film_mcs.rental_period (date_begin, date_end, id_film_format) values ('2018-01-01', '2018-01-14', 1);
insert into film_mcs.rental_period (date_begin, date_end, id_film_format) values ('2018-01-01', '2018-01-14', 2);
insert into film_mcs.rental_period (date_begin, date_end, id_film_format) values ('2018-01-07', '2018-01-21', 1);
insert into film_mcs.rental_period (date_begin, date_end, id_film_format) values ('2018-01-07', '2018-01-21', 2);

insert into film_mcs.film (name, duration, imdb) values ('Test 1', 120, 9.03);
insert into film_mcs.film (name, duration, imdb) values ('Test 2', 90, 5.53);
insert into film_mcs.film (name, duration, imdb) values ('Test 3', 110, 8);

insert into film_mcs.age_restriction_film (id_film, id_age_restriction) values (1, 1);
insert into film_mcs.age_restriction_film (id_film, id_age_restriction) values (2, 2);
insert into film_mcs.age_restriction_film (id_film, id_age_restriction) values (3, 1);

insert into film_mcs.country_film (id_film, id_country) values (1, 1);
insert into film_mcs.country_film (id_film, id_country) values (2, 1);
insert into film_mcs.country_film (id_film, id_country) values (2, 2);
insert into film_mcs.country_film (id_film, id_country) values (3, 3);

insert into film_mcs.genre_film (id_film, id_genre) values (1, 3);
insert into film_mcs.genre_film (id_film, id_genre) values (1, 2);
insert into film_mcs.genre_film (id_film, id_genre) values (2, 1);
insert into film_mcs.genre_film (id_film, id_genre) values (2, 2);