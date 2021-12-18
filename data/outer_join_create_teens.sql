CREATE TABLE teens(
	id serial primary key,
	name text,
    gender varchar(10)
);

insert into teens(name, gender) values
('Olga', 'female'), ('Anna', 'female'),
('Oleg', 'male'), ('Petr', 'male');


SELECT n1.name as femaly_name, n1.gender, n2.name as male_name, n2.gender
FROM teens n1 cross join teens n2
WHERE n1.gender != n2.gender AND n1.gender = 'female';