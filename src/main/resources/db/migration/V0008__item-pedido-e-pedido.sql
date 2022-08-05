create table solicitation (
    id SERIAL PRIMARY KEY,
    subtotal decimal(10,2) not null,
    taxa_frete decimal(10,2) not null,
    valor_total decimal(10,2) not null,

    restaurant_id bigint not null,
    usuario_cliente_id bigint not null,
    paymentform_id bigint not null,

    address_city_id bigint not null,
    address_cep varchar(9) not null,
    address_logradouro varchar(100) not null,
    address_numero varchar(20) not null,
    address_complemento varchar(60) null,
    address_bairro varchar(60) not null,

    status varchar(10) not null,
    data_criacao timestamp not null,
    data_confirmacao timestamp null,
    data_cancelamento timestamp null,
    data_delivery timestamp null

);

create table solicitation_item (
    id SERIAL PRIMARY KEY,
    quantidade smallint not null,
    preco_unitario decimal(10,2) not null,
    preco_total decimal(10,2) not null,
    observacao varchar(255) null,
    solicitation_id bigint not null,
    menu_id bigint not null,

    constraint solicitation_item_menu_unique UNIQUE (solicitation_id, menu_id)

);

alter table solicitation add constraint fk_solicitation_restaurant
foreign key (restaurant_id) references restaurant (id);

alter table solicitation add constraint fk_solicitation_usuario_cliente
foreign key (usuario_cliente_id) references usertuyofood (id);

alter table solicitation add constraint fk_solicitation_paymentform
foreign key (paymentform_id) references paymentform (id);

alter table solicitation_item add constraint fk_solicitation_item_solicitation
foreign key (solicitation_id) references solicitation (id);

alter table solicitation_item add constraint fk_solicitation_item_menu
foreign key (menu_id) references menu (id);