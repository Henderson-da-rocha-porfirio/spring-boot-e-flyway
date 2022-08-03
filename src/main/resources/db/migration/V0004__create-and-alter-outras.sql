create table paymentform (
	id SERIAL PRIMARY KEY,
	descricao varchar(60) not null
);

create table bundle (
	id SERIAL PRIMARY KEY,
	nome varchar(60) not null
);

create table permission_bundle (
	bundle_id bigint not null,
	permission_id bigint not null,

	primary key (bundle_id, permission_id)
);

create table permission (
	id SERIAL PRIMARY KEY,
	descricao varchar(60) not null,
	nome varchar(100) not null
);

create table restaurant (
	id SERIAL PRIMARY KEY,
	kitchen_id bigint not null,
	nome varchar(80) not null,
	taxa_frete decimal(10,2) not null,
	data_atualizacao timestamp not null,
	data_cadastro timestamp not null,

	address_city_id bigint,
	address_cep varchar(9),
	address_logradouro varchar(100),
	address_numero varchar(20),
	address_complemento varchar(60),
	address_bairro varchar(60)
);

create table restaurant_paymentform (
	restaurant_id bigint not null,
	paymentform_id bigint not null,

	primary key (restaurant_id, paymentform_id)
);

create table usertuyofood (
	id SERIAL PRIMARY KEY,
	nome varchar(80) not null,
	email varchar(255) not null,
	senha varchar(255) not null,
	data_cadastro timestamp not null
);

create table usertuyofood_bundle (
	usertuyofood_id bigint not null,
	bundle_id bigint not null,

	primary key (usertuyofood_id, bundle_id)
);




alter table permission_bundle add constraint fk_permission_bundle_permission
foreign key (permission_id) references permission (id);

alter table permission_bundle add constraint fk_permission_bundle_bundle
foreign key (bundle_id) references bundle (id);

alter table restaurant add constraint fk_restaurant_kitchen
foreign key (kitchen_id) references kitchen (id);

alter table restaurant add constraint fk_restaurant_city
foreign key (address_city_id) references city (id);

alter table restaurant_paymentform add constraint fk_rest_forma_pagto_forma_pagto
foreign key (paymentform_id) references paymentform (id);

alter table restaurant_paymentform add constraint fk_rest_forma_pagto_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table usertuyofood_bundle add constraint fk_usertuyofood_bundle_bundle
foreign key (bundle_id) references bundle (id);

alter table usertuyofood_bundle add constraint fk_usertuyofood_bundle_usertuyofood
foreign key (usertuyofood_id) references usertuyofood (id);