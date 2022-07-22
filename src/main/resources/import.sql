insert into kitchen (id, nome) values (1, 'Tailandesa');
insert into kitchen (id, nome) values (2, 'Indiana');
insert into kitchen (id, nome) values (3, 'Argentina');
insert into kitchen (id, nome) values (4, 'Portuguesa');

insert into state (id, nome) values (1, 'Minas Gerais');
insert into state (id, nome) values (2, 'São Paulo');
insert into state (id, nome) values (3, 'Ceará');

insert into city (id, nome, state_id) values (1, 'Uberlândia', 1);
insert into city (id, nome, state_id) values (2, 'Belo Horizonte', 1);
insert into city (id, nome, state_id) values (3, 'São Paulo', 2);
insert into city (id, nome, state_id) values (4, 'Campinas', 2);
insert into city (id, nome, state_id) values (5, 'Fortaleza', 3);

insert into restaurant (id, nome, taxa_frete, kitchen_id, data_cadastro, data_atualizacao, address_city_id, address_cep, address_logradouro, address_numero, address_bairro) values (1, 'Thai Gourmet', 10, 1, now()::timestamp, now()::timestamp, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
insert into restaurant (id, nome, taxa_frete, kitchen_id, data_cadastro, data_atualizacao) values (2, 'Thai Delivery', 9.50, 1, now()::timestamp, now()::timestamp);
insert into restaurant (id, nome, taxa_frete, kitchen_id, data_cadastro, data_atualizacao) values (3, 'Tuk Tuk Comida Indiana', 15, 2, now()::timestamp, now()::timestamp);
insert into restaurant (id, nome, taxa_frete, kitchen_id,  data_cadastro, data_atualizacao) values (4, 'Solar Argentina', 45, 3, now()::timestamp, now()::timestamp);
insert into restaurant (id, nome, taxa_frete, kitchen_id, data_cadastro, data_atualizacao) values (5, 'O Portugues', 16, 4, now()::timestamp, now()::timestamp);
insert into restaurant (id, nome, taxa_frete, kitchen_id,  data_cadastro, data_atualizacao) values (6, 'Camarao de Portuga', 44, 4, now()::timestamp, now()::timestamp);

-- Exemplo com mysql:
-- insert into restaurant (id, nome, taxa_frete, kitchen_id,  data_cadastro, data_atualizacao) values (6, 'Camarao de Portuga', 44, 4, utc_timestamp, utc_timestamp);

-- insert into restaurant (id, nome, taxa_frete, kitchen_id, address_city_id, address_cep, address_logradouro, address_numero, address_bairro) values (1, 'Big Batatas', 18, 1, 1, '38400-999', 'Rua João Pinheiro', '1000', 'Centro');
-- insert into restaurant (id, nome, taxa_frete, kitchen_id) values (2, 'Mannupí', 10, 1);
-- insert into restaurant (id, nome, taxa_frete, kitchen_id) values (3, 'Cairpira', 10, 2);
-- insert into restaurant (id, nome, taxa_frete, kitchen_id) values (4, 'Bologna Restaurantes', 45, 2);



 insert into payment_form (id, descricao) values (1, 'Cartão de crédito');
 insert into payment_form (id, descricao) values (2, 'Cartão de débito');
 insert into payment_form (id, descricao) values (3, 'Dinheiro');

 insert into permission (id, nome, descricao) values (1, 'CONSULTAR_COZINHAS', 'Permite consultar cozinhas');
 insert into permission (id, nome, descricao) values (2, 'EDITAR_COZINHAS', 'Permite editar cozinhas');

 insert into restaurant_payment_form (restaurant_id, payment_form_id) values (1, 1), (1, 2), (1, 3), (2, 3), (3, 2), (3, 3), (4, 1), (4, 2), (5, 1), (5, 2), (6, 3);

insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('Porco com molho agridoce', 'Deliciosa carne suína ao molho especial', 78.90, 1, 1);
insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('Camarão tailandês', '16 camarões grandes ao molho picante', 110, 1, 1);

insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('Salada picante com carne grelhada', 'Salada de folhas com cortes finos de carne bovina grelhada e nosso molho especial de pimenta vermelha', 87.20, 1, 2);

insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('Garlic Naan', 'Pão tradicional indiano com cobertura de alho', 21, 1, 3);
insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('Murg Curry', 'Cubos de frango preparados com molho curry e especiarias', 43, 1, 3);

insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('Bife Ancho', 'Corte macio e suculento, com dois dedos de espessura, retirado da parte dianteira do contrafilé', 79, 1, 4);
insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('T-Bone', 'Corte muito saboroso, com um osso em formato de T, sendo de um lado o contrafilé e do outro o filé mignon', 89, 1, 4);

insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('Sanduíche X-Tudo', 'Sandubão com muito queijo, hamburger bovino, bacon, ovo, salada e maionese', 19, 1, 5);

insert into menu (nome, descricao, preco, ativo, restaurant_id) values ('Espetinho de Cupim', 'Acompanha farinha, mandioca e vinagrete', 8, 1, 6);
