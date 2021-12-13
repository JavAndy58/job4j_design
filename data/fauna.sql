insert into fauna(name, avg_age, discovery_date) values ('Lion', 12600, '01.01.1800');
insert into fauna(name, avg_age, discovery_date) values ('Swordfish', 4500, '01.01.1850');
insert into fauna(name, avg_age, discovery_date) values ('Honey badger', 8500, null);

select * from fauna where name like '%fish%';

select * from fauna where avg_age > 10000 AND avg_age < 21000;

select * from fauna where discovery_date is null;

select * from fauna where discovery_date < '01.01.1950';