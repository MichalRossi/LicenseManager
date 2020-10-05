
# insert seasons
ALTER TABLE seasons AUTO_INCREMENT = 1;
insert into seasons(start_year,end_year) values (2010,2011);
insert into seasons(start_year,end_year) values (2011,2012);
insert into seasons(start_year,end_year) values (2012,2013);
insert into seasons(start_year,end_year) values (2013,2014);
insert into seasons(start_year,end_year) values (2014,2015);
insert into seasons(start_year,end_year) values (2015,2016);
insert into seasons(start_year,end_year) values (2016,2017);
insert into seasons(start_year,end_year) values (2017,2018);
insert into seasons(start_year,end_year) values (2018,2019);
insert into seasons(start_year,end_year) values (2019,2020);
insert into seasons(start_year,end_year) values (2020,2021);

# insert clubs
ALTER TABLE clubs AUTO_INCREMENT = 1;
insert into clubs(address_city, address_street_name, address_street_number, address_zip_code, age_category, email, name, nip, phone_number, web_address)
values('Warszawa', 'Marymoncka', 34, 01318, 'senior', 'kontaktazsrugby@gmail.com', 'AZS AWF Warszawa', '1181094524', '518788821', 'https://www.rugbyazs.pl');
insert into clubs(address_city, address_street_name, address_street_number, address_zip_code, age_category, email, name, nip, phone_number, web_address)
values('Krakow', 'Na Bloniach', 7, 30213, 'senior', 'biuro@juvenia.info', 'RzKS Juvenia', '6771200792', '509944904', 'https://juvenia.info');
insert into clubs(address_city, address_street_name, address_street_number, address_zip_code, age_category, email, name, nip, phone_number, web_address)
values('Sopot', 'Jana z Kolna', 18, 81741, 'senior', 'ogniwo@ogniwosopot.pl', 'MKS Ogniwo Sopot', '5851210912', '795944441', 'hhttps://www.ogniwosopot.pl');
insert into clubs(address_city, address_street_name, address_street_number, address_zip_code, age_category, email, name, nip, phone_number, web_address)
values('Poznan', 'Slowianska', 78, 61664, 'senior', 'obiekty@posnania.pl', 'KS Posnania', '7770003417', '601754463', 'https://www.posnania.pl');
insert into clubs(address_city, address_street_name, address_street_number, address_zip_code, age_category, email, name, nip, phone_number, web_address)
values('Gdynia', 'Gorskiego', 10, 81302, 'senior', 'biuro@arkarugby.pl', 'RC Arka Gdynia', '5861976024', '602644858', 'https://arkarugby.pl');
insert into clubs(address_city, address_street_name, address_street_number, address_zip_code, age_category, email, name, nip, phone_number, web_address)
values('Lublin', 'Krasinskiego', 11, 20709, 'U20', 'budowlanilublin@op.pl ', 'KS Budowlani Lublin', '7121933680', '510141406', 'https://www.ksbudowlani.com');


# insert coaches
ALTER TABLE coaches AUTO_INCREMENT = 1;
insert into coaches(age_category, first_name, gender, last_name, valid)
values ('senior' , 'Adam' , 'MALE', 'Adamski', true);
insert into coaches(age_category, first_name, gender, last_name, valid)
values ('U16' , 'Karyna' , 'FEMALE', 'Adamska', true);
insert into coaches(age_category, first_name, gender, last_name, valid)
values ('senior' , 'Andrzej' , 'MALE', 'Kopyc', true);
insert into coaches(age_category, first_name, gender, last_name, valid)
values ('U14' , 'Ada' , 'FEMALE', 'Kowalska', true);
insert into coaches(age_category, first_name, gender, last_name, valid)
values ('senior' , 'Tomasz' , 'MALE', 'Stepien', true);
insert into coaches(age_category, first_name, gender, last_name, valid)
values ('senior' , 'Karol' , 'MALE', 'Kopyra', true);
insert into coaches(age_category, first_name, gender, last_name, valid)
values ('senior' , 'Marcin' , 'MALE', 'Daniel', true);
insert into coaches(age_category, first_name, gender, last_name, valid)
values ('senior' , 'Maciej' , 'MALE', 'Mis', true);

# list of coaches_seasons should be the same length as coaches_clubs
# insert coaches_clubs & coaches_seasons
ALTER TABLE coaches_clubs AUTO_INCREMENT = 1;
ALTER TABLE coaches_seasons AUTO_INCREMENT = 1;
insert into coaches_clubs(coaches_id, clubs_id) VALUES (1,2),(1,3),(1,4);
insert into coaches_seasons(coaches_id, seasons_id) VALUES (1,3),(1,5),(1,8);

insert into coaches_clubs(coaches_id, clubs_id) VALUES (2,4),(1,5);
insert into coaches_seasons(coaches_id, seasons_id) VALUES (2,8),(2,10);

insert into coaches_clubs(coaches_id, clubs_id) VALUES (3,1),(3,4);
insert into coaches_seasons(coaches_id, seasons_id) VALUES (3,7),(3,10);

insert into coaches_clubs(coaches_id, clubs_id) VALUES (4,1),(4,2),(4,5),(4,1);
insert into coaches_seasons(coaches_id, seasons_id) VALUES (4,1),(4,4),(4,6),(4,10);

insert into coaches_clubs(coaches_id, clubs_id) VALUES (5,3),(5,4),(5,6);
insert into coaches_seasons(coaches_id, seasons_id) VALUES (5,8),(5,9),(5,10);

insert into coaches_clubs(coaches_id, clubs_id) VALUES (6,1),(6,2),(6,1);
insert into coaches_seasons(coaches_id, seasons_id) VALUES (6,4),(6,6),(6,8);

insert into coaches_clubs(coaches_id, clubs_id) VALUES (7,1);
insert into coaches_seasons(coaches_id, seasons_id) VALUES (7,11);

insert into coaches_clubs(coaches_id, clubs_id) VALUES (8,1);
insert into coaches_seasons(coaches_id, seasons_id) VALUES (8,11);

# insert players
ALTER TABLE players AUTO_INCREMENT = 1;
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('senior' , '1992-08-03' ,'Karol' , 'MALE', 'Kacprzak', true);
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('U12' , '2010-08-03' ,'Aldona' , 'FEMALE', 'Mokrzycka', true);
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('senior' , '1980-11-03' ,'Karolina' , 'FEMALE', 'Kopacka', true);
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('senior' , '1988-10-12 ','Adam' , 'MALE', 'Dziwisz', false);
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('senior' , '1984-10-12' ,'Sebastian' , 'MALE', 'Sosnowski', true);
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('senior' , '1972-10-12' ,'Wladyslaw' , 'MALE', 'Komar', true);
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('U20' , '2000-10-12' ,'Sylwester' , 'MALE', 'Tomczyk', true);
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('senior' , '1993-09-12' ,'Jakub' , 'MALE', 'Kopczyk', true);
insert into players(age_category, date_of_birth, first_name, gender, last_name, valid)
values ('senior' , '1991-10-12' ,'Tomasz' , 'MALE', 'Gryglewski', true);

# list of players_seasons should be the same length as players_clubs
# insert players_clubs & players_seasons
ALTER TABLE players_clubs AUTO_INCREMENT = 1;
ALTER TABLE players_seasons AUTO_INCREMENT = 1;
insert into players_clubs(players_id, clubs_id) VALUES (1,2),(1,3),(1,4);
insert into players_seasons(players_id, seasons_id) VALUES (1,3),(1,5),(1,8);

insert into players_clubs(players_id, clubs_id) VALUES (2,4),(1,5);
insert into players_seasons(players_id, seasons_id) VALUES (2,8),(2,10);

insert into players_clubs(players_id, clubs_id) VALUES (3,1),(3,4);
insert into players_seasons(players_id, seasons_id) VALUES (3,7),(3,10);

insert into players_clubs(players_id, clubs_id) VALUES (4,1),(4,2),(4,5),(4,1);
insert into players_seasons(players_id, seasons_id) VALUES (4,1),(4,4),(4,6),(4,10);

insert into players_clubs(players_id, clubs_id) VALUES (5,3),(5,4),(5,6);
insert into players_seasons(players_id, seasons_id) VALUES (5,8),(5,9),(5,10);

insert into players_clubs(players_id, clubs_id) VALUES (6,1),(6,2),(6,1);
insert into players_seasons(players_id, seasons_id) VALUES (6,4),(6,6),(6,8);

insert into players_clubs(players_id, clubs_id) VALUES (7,1);
insert into players_seasons(players_id, seasons_id) VALUES (7,11);

insert into players_clubs(players_id, clubs_id) VALUES (8,1);
insert into players_seasons(players_id, seasons_id) VALUES (8,11);

insert into players_clubs(players_id, clubs_id) VALUES (9,1),(9,5),(9,6);
insert into players_seasons(players_id, seasons_id) VALUES (9,2),(9,10),(9,11);