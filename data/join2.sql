create table car_numbers(
	id serial primary key,
	number int,
	region int
);

create table cars (
	id serial primary key,
	name text,
	car_numbers_id int references car_numbers(id) unique
);

insert into car_numbers(number, region) values (171, 77);
insert into car_numbers(number, region) values (285, 100);
insert into car_numbers(number, regiom) values (777, 77);

insert into cars(name, car_numbers_id) values ('Mazda', 1);
insert into cars(name, car_numbers_id) values ('Opel', 2);
insert into cars(name, car_numbers_id) values ('GM', 3);