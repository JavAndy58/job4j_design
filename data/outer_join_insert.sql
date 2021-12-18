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
FROM emploers
LEFT JOIN departments
ON departments.id = emploers.department_id
WHERE departments.name is null;

-- № 4
SELECT emploers.name, departments.name
FROM departments
LEFT JOIN emploers
ON departments.id = emploers.department_id;

SELECT emploers.name, departments.name
FROM emploers
RIGHT JOIN departments
ON departments.id = emploers.department_id;







