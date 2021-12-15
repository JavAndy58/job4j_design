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


INSERT INTO devices(name, price)
VALUES ('Smartphone', 12550), ('TV', 25800), ('Ps4', 20190), ('Projector', 33000);
INSERT INTO people(name)
VALUES ('Olga'), ('Petr'), ('Egor'), ('Petr');
INSERT INTO devices_people(device_id, people_id)
VALUES (1, 1), (1, 2), (2, 1), (2, 3), (3, 3), (3, 4), (4, 4);