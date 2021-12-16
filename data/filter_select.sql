SELECT name
FROM type;

SELECT name
FROM product
WHERE name LIKE '%мороженное%';

SELECT name
FROM product
WHERE expired_date > '01.03.2021';

SELECT name, price
FROM product
ORDER BY price desc
LIMIT 1;

SELECT t.name, count(p.name)
FROM product as p
JOIN type as t
ON p.type_id = t.id
GROUP BY t.name;


