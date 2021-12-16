insert into users(name) values ('Ivan');
insert into users(name) values ('Oleg');

insert into category(name) values ('urgent');

insert into state(name) values ('issued');

insert into role(name) values ('Роли');

insert into rules(name) values ('Правила');

insert into item(name, users_id, category_id, state_id) values ('Mazda', 2, 1, 1);

insert into commens(name, item_id) values ('Нормуль', 1);

insert into attachs(name, item_id) values ('Выдано', 1);

insert into role_rules(role_id, rules_id) values (1, 1);
