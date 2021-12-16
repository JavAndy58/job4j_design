CREATE TABLE type(
	id serial primary key,
	name text
);

CREATE TABLE product(
	id serial primary key,
	name text,
	expired_date timestamp,
	price float,
	type_id int references type(id) 
);

INSERT INTO type(name) values ('СЫР'), ('МОЛОКО'), ('ЧАЙ');
INSERT INTO product(name, expired_date, price, type_id) values 
('плавленный', '01.01.2021', 150, 1), ('моцарелла', '01.04.2021', 350, 1), ('мороженное', '01.03.2021', 50, 2), 
('молоко деревенское', '01.06.2021', 100, 2), ('чай черный', '01.03.2021', 260, 3), ('чай зеленый', '01.03.2021', 280, 3);