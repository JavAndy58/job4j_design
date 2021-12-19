SELECT cars.name,bodys.name, engines.name,transmissions.name
FROM transmissions
LEFT JOIN cars ON cars.transmission_id = transmissions.id
LEFT JOIN engines ON cars.engine_id = engines.id
LEFT JOIN bodys ON cars.body_id = bodys.id
WHERE cars.name is not null;

SELECT transmissions.name
FROM transmissions
LEFT JOIN cars ON cars.transmission_id = transmissions.id
LEFT JOIN engines ON cars.engine_id = engines.id
LEFT JOIN bodys ON cars.body_id = bodys.id
WHERE transmissions.name is not null AND cars.name is null;

SELECT bodys.name
FROM bodys
LEFT JOIN cars ON cars.transmission_id = bodys.id
LEFT JOIN engines ON cars.engine_id = engines.id
LEFT JOIN transmissions ON cars.transmission_id = transmissions.id
WHERE bodys.name is not null AND cars.name is null;

SELECT engines.name
FROM engines
LEFT JOIN cars ON cars.engine_id = engines.id
LEFT JOIN bodys ON cars.body_id = bodys.id
LEFT JOIN transmissions ON cars.transmission_id = transmissions.id
WHERE engines.name is not null AND cars.name is null;