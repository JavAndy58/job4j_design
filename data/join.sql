select * from car_numbers;
select * from 

select cs.name, cn.number
from cars as cs
join car_numbers as cn
on cs.car_numbers_id = cn.id;

select cs.name, cn.number, cn.region
from cars as cs
join car_numbers as cn
on cs.car_numbers_id = cn.id;

select cs.name, cn.number, cn.region
from cars as cs
join car_numbers as cn
on cs.car_numbers_id = cn.id
where cn.region = 77;
