CREATE TABLE bodys(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE engines(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE transmissions(
	id serial primary key,
	name varchar(255)
);

CREATE TABLE cars(
	id serial primary key,
	name varchar(255),
	body_id int references bodys(id),
	engine_id int references engines(id),
	transmission_id int references transmissions(id)
);

INSERT INTO bodys(name) VALUES ('sedan'), ('hatchback'), ('wagon');
INSERT INTO engines(name) VALUES ('internal conbustion'), ('composite');
INSERT INTO transmissions(name) VALUES ('automatic'), ('mechanical'), ('variable');
INSERT INTO cars(name, body_id, engine_id, transmission_id) 
VALUES ('Opel', 1, 1, 2), ('Mazda', 2, 1, 1), ('Niva', 3, 1, 2);