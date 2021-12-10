create table carss(
    id serial primary key,
    brand varchar(255),
    color text,
    power int,
    sport_car boolean
);
select * from carss;
insert into carss(brand, color, power, sport_car) values('Mazda', 'Red', '100', 'false'); 
select * from carss;
update carss set brand = 'Opel';
select * from carss;
delete from carss;
select * from carss;