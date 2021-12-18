
SELECT *
FROM departments
LEFT JOIN emploers
ON departments.id = emploers.department_id;

SELECT *
FROM departments
RIGHT JOIN emploers
ON departments.id = emploers.department_id;

SELECT *
FROM departments
FULL JOIN emploers
ON departments.id = emploers.department_id;

SELECT *
FROM departments
CROSS JOIN emploers;

-- № 3
SELECT *
FROM departments
LEFT JOIN emploers
ON departments.id = emploers.department_id
WHERE departments.name is null;

-- № 4
SELECT *
FROM departments
LEFT JOIN emploers
ON departments.id = emploers.department_id;

SELECT *
FROM emploers
RIGHT JOIN departments
ON departments.id = emploers.department_id;









