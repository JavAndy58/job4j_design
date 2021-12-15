SELECT avg(price)
FROM devices;

SELECT p.name, avg(d.price)
FROM people as p
JOIN devices_people as dp
ON dp.people_id = p.id
JOIN devices as d
ON dp.device_id = d.id
GROUP BY p.name

SELECT p.name, avg(d.price)
FROM people as p
JOIN devices_people as dp
ON dp.people_id = p.id
JOIN devices as d
ON dp.device_id = d.id
GROUP BY p.name
HAVING avg(d.price) > 5000;