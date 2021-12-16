create table devices(
	id serial primary key,
	name varchar(255),
	price float
);

create table people(
	id serial primary key,
	name varchar(255)
);

create table devices_people(
	id serial primary key,
	device_id int references devices(id),
	people_id int references people(id)
);

insert into devices(name, price) 
values ('Smartphone', 10250), ('TV', 20550), ('Radio', 1500), ('Antiradar', 5600);
insert into people(name)
values ('Olga'), ('Egor'), ('Igor'), ('Petr');
insert into devices_people(device_id, people_id)
values (1, 4), (2, 3), (3, 4), (4, 2);

