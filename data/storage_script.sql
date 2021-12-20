SELECT cars.name,bodys.name, engines.name,transmissions.name
FROM transmissions
JOIN cars ON cars.transmission_id = transmissions.id
JOIN engines ON cars.engine_id = engines.id
JOIN bodys ON cars.body_id = bodys.id;

SELECT transmissions.name
FROM transmissions
LEFT JOIN cars ON cars.transmission_id = transmissions.id
WHERE cars.name is null;

SELECT bodys.name
FROM bodys
LEFT JOIN cars ON cars.body_id = bodys.id
WHERE cars.name is null;

SELECT engines.name
FROM engines
LEFT JOIN cars ON cars.engine_id = engines.id
WHERE cars.name is null;