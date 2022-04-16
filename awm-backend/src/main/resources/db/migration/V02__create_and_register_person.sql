CREATE TABLE person (
	id BIGINT(50) PRIMARY KEY AUTO_INCREMENT,
	name VARCHAR(50) NOT NULL,
    is_active BOOLEAN NOT NULL,
    logradouro VARCHAR(30),
    numero VARCHAR(30),
    complemento VARCHAR(30),
    bairro VARCHAR(30),
    cep VARCHAR(30),
    cidade VARCHAR(30),
    estado VARCHAR(2)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO person 
(name, is_active, logradouro, numero, bairro, cep, cidade, estado) 
VALUES ("Stefano Souza", true, "Rua 78", "194", "Jereissati II", "61814-348", "Pacatuba", "CE");

INSERT INTO person 
(name, is_active, logradouro, numero, complemento, bairro, cep, cidade, estado) 
VALUES ("Luiz Figo", true, "Rua Dr. Augusto Fialho", "720", "Altos", "Sir Nicolas", "112665-658", "Distrito São Pedro", "SC");


INSERT INTO person 
(name, is_active, logradouro, numero, complemento, bairro, cidade, estado) 
VALUES ("Izabela Soares", true, "Avenida Ezequiel de Alencar", "1066", "10° andar", "Antonio Bittenccour", "Ubatão", "SP");

INSERT INTO person 
(name, is_active, logradouro, numero, bairro, cidade, estado) 
VALUES ("João Cristaldo", false, "Rua Francisco Rodovalho", "78", "Parque Araxá", "Fortaleza", "CE");