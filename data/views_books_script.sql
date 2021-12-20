create view show_Ivan_autors as
select s.name as student, a.name as author 
from students as s
join orders o on s.id = o.student_id
join books b on o.book_id = b.id
join authors a on b.author_id = a.id
group by (s.name, a.name)
having s.name = 'Иван Иванов'

select * from show_Ivan_autors