CREATE TABLE release_tb (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description_rls VARCHAR(50) NOT NULL,
	due_date DATE NOT NULL,
	payment_date DATE,
	value_rls DECIMAL(10,2) NOT NULL,
	note VARCHAR(100),
	type_rls VARCHAR(20) NOT NULL,
	id_category BIGINT(20) NOT NULL,
	id_person BIGINT(20) NOT NULL,
	FOREIGN KEY (id_category) REFERENCES category(id),
	FOREIGN KEY (id_person) REFERENCES person(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Salário mensal', '2017-06-10', null, 6500.00, 'Distribuição de lucros', 'RECEITA', 1, 1);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Bahamas', '2017-02-10', '2017-02-10', 100.32, null, 'DESPESA', 2, 2);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Top Club', '2017-06-10', null, 120, null, 'RECEITA', 3, 3);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('CEMIG', '2017-02-10', '2017-02-10', 110.44, 'Geração', 'RECEITA', 3, 4);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('DMAE', '2017-06-10', null, 200.30, null, 'DESPESA', 3, 2);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Extra', '2017-03-10', '2017-03-10', 1010.32, null, 'RECEITA', 4, 3);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Bahamas', '2017-06-10', null, 500, null, 'RECEITA', 1, 2);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Top Club', '2017-03-10', '2017-03-10', 400.32, null, 'DESPESA', 4, 4);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Despachante', '2017-06-10', null, 123.64, 'Multas', 'DESPESA', 3, 1);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Pneus', '2017-04-10', '2017-04-10', 665.33, null, 'RECEITA', 5, 1);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Café', '2017-06-10', null, 8.32, null, 'DESPESA', 1, 3);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Eletrônicos', '2017-04-10', '2017-04-10', 2100.32, null, 'DESPESA', 5, 4);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Instrumentos', '2017-06-10', null, 1040.32, null, 'DESPESA', 4, 3);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Café', '2017-04-10', '2017-04-10', 4.32, null, 'DESPESA', 4, 2);
INSERT INTO release_tb (description_rls, due_date, payment_date, value_rls, note, type_rls, id_category, id_person) VALUES ('Lanche', '2017-06-10', null, 10.20, null, 'DESPESA', 4, 1);