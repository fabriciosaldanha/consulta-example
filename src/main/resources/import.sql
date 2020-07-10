insert into estado (id, nome) values (1, 'Para');
insert into estado (id, nome) values (2, 'Sao Paulo');
insert into estado (id, nome) values (3, 'Rio de Janeiro');

insert into cidade (id, nome, estado_id) values (1, 'Belem', 1);
insert into cidade (id, nome, estado_id) values (2, 'Ananindeua', 1);
insert into cidade (id, nome, estado_id) values (3, 'Sao Paulo', 2);
insert into cidade (id, nome, estado_id) values (4, 'Campinas', 2);
insert into cidade (id, nome, estado_id) values (5, 'Rio de Janeiro', 3);
insert into cidade (id, nome, estado_id) values (6, 'Niteroi', 3);

insert into cliente (id, nome, cidade_id) values (1, 'Fulano da Silva', 2);
insert into cliente (id, nome, cidade_id) values (2, 'Beltrano da Silva', 4);
insert into cliente (id, nome, cidade_id) values (3, 'Sicrano da Silva', 6);

insert into empresa (id, nome, cidade_id) values (1, 'Americanas', 1);
insert into empresa (id, nome, cidade_id) values (2, 'Magazine Luiza', 3);
insert into empresa (id, nome, cidade_id) values (3, 'Casas Bahia', 5);

insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(1, 1, '2019-10-01', 100.00, 1, 1);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(2, 2, '2019-10-10', 200.00, 2, 2);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(3, 3, '2019-10-20', 300.00, 1, 3);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(4, 4, '2019-10-31', 400.00, 3, 1);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(5, 5, '2019-11-01', 500.00, 3, 2);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(6, 6, '2019-11-10', 600.00, 2, 3);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(7, 7, '2019-11-20', 700.00, 1, 1);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(8, 8, '2019-11-30', 800.00, 2, 2);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(9, 9, '2019-12-01', 900.00, 3, 3);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(10, 10, '2019-12-10', 1000.00, 2, 1);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(11, 11, '2019-12-20', 1100.00, 1, 2);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(12, 12, '2019-12-31', 1200.00, 2, 3);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(13, 13, '2020-01-01', 1300.00, 1, 1);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(14, 14, '2020-01-10', 1400.00, 3, 2);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(15, 15, '2020-01-20', 1500.00, 3, 3);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(16, 16, '2020-01-31', 1600.00, 1, 1);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(17, 17, '2020-02-01', 1700.00, 2, 2);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(18, 18, '2020-02-10', 1800.00, 2, 3);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(19, 19, '2020-02-20', 1900.00, 1, 1);
insert into nota_fiscal(id, numero, data_emissao, valor, empresa_id, cliente_id) values(20, 20, '2020-02-29', 2000.00, 1, 2);