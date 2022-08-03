 create table menu (
	id SERIAL PRIMARY KEY,
	restaurant_id bigint not null,
	nome varchar(80) not null,
	descricao text not null,
	preco decimal(10,2) not null,
	ativo smallint not null
);


alter table menu add constraint fk_menu_restaurant
foreign key (restaurant_id) references restaurant (id);