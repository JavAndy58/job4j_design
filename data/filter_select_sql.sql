SELECT p.name, t.name
FROM type as t
JOIN product as p
ON p.type_id = t.id
WHERE t.name = 'СЫР'

SELECT name
FROM product
WHERE name LIKE '%мороженное%';

SELECT name
FROM product
WHERE current_date > expired_date;

SELECT name, price
FROM product
WHERE price = (SELECT max(price) FROM product)

SELECT t.name, count(p.name)
FROM product as p
JOIN type as t
ON p.type_id = t.id
GROUP BY t.name;

SELECT p.name, t.name
FROM product as p
JOIN type as t
ON p.type_id = t.id
WHERE t.name = 'СЫР' OR t.name = 'МОЛОКО'

SELECT t.name, count(t.name) 
FROM product as p
JOIN type as t
ON p.type_id = t.id
GROUP BY t.name
HAVING count(t.name) < 10

SELECT p.name, t.name
FROM product as p
JOIN type as t
ON p.type_id = t.id