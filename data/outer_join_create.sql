CREATE TABLE departments(
	id serial primary key,
	name text
);

CREATE TABLE emploers(
	id serial primary key,
	name text,
	department_id int references departments(id)
);

insert into departments(name) values ('D1'), ('D2'), ('D3');

insert into emploers(name, department_id) values
('Emploer1', 1), ('Emploer2', 2), ('Emploer3', 1), 
('Emploer4', 3), ('Emploer5', null);


