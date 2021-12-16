create table parking(
    id serial primary key,
    address text
);

create table car_mazda(
    id serial primary key,
    model text,
    power int,
    parking_id int references parking(id)
);

insert into parking (address) values ('Penza');
insert into car_mazda (model, power, parking_id) values ('CX-5', '150', 1);

create table scores(
    scores_id serial primary key,
    title varchar(255)  
);

create table humans(
    humans_id serial primary key,
    name_human varchar(255)
);

create table humans_scores(
    humans_scores_id serial primary key,
    score_id int references scores(scores_id),
    human_id int references humans(humans_id)
);

insert into scores(title) values ('Spar');
insert into scores(title) values ('Metro');
insert into scores(title) values ('DNS');

insert into humans(name_human) values ('Ivan'); 
insert into humans(name_human) values ('Petr');
insert into humans(name_human) values ('Roman');

insert into humans_scores(score_id, human_id) values (1, 1);
insert into humans_scores(score_id, human_id) values (2, 2);
insert into humans_scores(score_id, human_id) values (2, 3);
insert into humans_scores(score_id, human_id) values (3, 3);


create table wifes(
    wifes_id serial primary key,
    name_wife text
);

create table husbands(
    husbands_id serial primary key,
    name_husband text 
);

create table wifes_husbands(
    wife_husband_id serial primary key,
    wife_id int references wifes(wifes_id) unique ,
    husband_id int references husbands(husbands_id) unique 
);

insert into wifes(name_wife) values ('Marina');
insert into wifes(name_wife) values ('Olga');
insert into wifes(name_wife) values ('Elena');

insert into husbands(name_husband) values ('Egor');
insert into husbands(name_husband) values ('Ivan');
insert into husbands(name_husband) values ('Petr');

insert into wifes_husbands(wife_id, husband_id) values (1, 1);
insert into wifes_husbands(wife_id, husband_id) values (2, 3);
insert into wifes_husbands(wife_id, husband_id) values (3, 2);
