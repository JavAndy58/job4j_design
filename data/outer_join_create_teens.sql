CREATE TABLE teens(
	id serial primary key,
	name text,
    gender varchar(10)
);

insert into teens(name, gender) values
('Olga', 'female'), ('Anna', 'female'),
('Oleg', 'male'), ('Petr', 'male');

SELECT g1.gender, g2.gender

SELECT *
FROM teens g1
CROSS JOIN teens g2
WHERE g2 != g1
