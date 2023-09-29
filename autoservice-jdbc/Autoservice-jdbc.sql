create table model_type (
	id int2 generated always as identity primary key,
	name varchar(50) unique not null
);

insert into model_type (name) values 
('audi'),
('bmw'),
('honda');


create table transport_type (
	id int2 generated always as identity primary key,
	name varchar(50) unique not null
);

insert into transport_type (name) values 
('auto'),
('bike'),
('bus');

create table client (
	id int4 generated always as identity primary key,
	first_name varchar(30) not null,
	last_name varchar(30) not null
);

insert into client (first_name, last_name) values ('Sergey', 'Makarenko');


create table transport (
	id int4 generated always as identity primary key,
	model_type_id int2 not null,
	transport_type_id int2 not null,
	client_id int4,

	constraint fk_transport_model_type_id foreign key (model_type_id) references model_type(id),
	constraint fk_transport_transport_type_id foreign key (transport_type_id) references transport_type(id),
	constraint fk_transport_client_id foreign key (client_id) references client(id)
);
insert into transport (model_type_id, transport_type_id, client_id) values 
(1, 1, 1),
(2, 3, 1),
(3, 2, 1);

select t.id, mt."name" as model, tt."name" as "type", c.first_name as "owner name", c.last_name as "owner family"  from transport t 
left join model_type mt on t.model_type_id = mt.id 
left join transport_type tt on t.transport_type_id = tt.id 
left join client c on t.client_id = c.id