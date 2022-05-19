insert into kitchen (id, nome) values (1, 'Tailandesa');
insert into kitchen (id, nome) values (2, 'Indiana');

insert into restaurant (id, nome, taxa_frete, kitchen_id) values ('Big Batatas', 18, 1);
insert into restaurant (id, nome, taxa_frete, kitchen_id) values ('Mannupí', 10, 1);
insert into restaurant (id, nome, taxa_frete, kitchen_id) values ('Cairpira', 10, 2);
insert into restaurant (id, nome, taxa_frete, kitchen_id) values ('Bologna Restaurantes', 45, 2);

 insert into state (id, nome) values (1, 'Minas Gerais');
 insert into state (id, nome) values (2, 'São Paulo');
 insert into state (id, nome) values (3, 'Ceará');

 insert into city (id, nome, state_id) values (1, 'Uberlândia', 1);
 insert into city (id, nome, state_id) values (2, 'Belo Horizonte', 1);
 insert into city (id, nome, state_id) values (3, 'São Paulo', 2);
 insert into city (id, nome, state_id) values (4, 'Campinas', 2);
 insert into city (id, nome, state_id) values (5, 'Fortaleza', 3);

 insert into payment_form (id, descricao) values (1, 'Cartão de crédito');
 insert into payment_form (id, descricao) values (2, 'Cartão de débito');
 insert into payment_form (id, descricao) values (3, 'Dinheiro');

 insert into permission (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
 insert into permission (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');
