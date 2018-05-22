CREATE TABLE MARCA(
	idMarca SERIAL,
	descricao VARCHAR(255),
	PRIMARY KEY(idMarca)
);

CREATE TABLE MODELO(
	
	idModelo SERIAL,
	descricao VARCHAR(255),
	idMarca INT NOT NULL,
	PRIMARY KEY(idModelo),
	FOREIGN KEY(idMarca) REFERENCES MARCA(idMarca)
)	

CREATE TABLE CLIENTE(
	
	idCliente SERIAL,
	nome VARCHAR(255),
	rg VARCHAR(9),
	cpf VARCHAR(11),
	datNasc DATE,
	telf INT,
	celular INT,
	logradouro VARCHAR(30),
	numero INT,
	bairro VARCHAR(30),
	cidade VARCHAR(30),
	estado VARCHAR(30),
	cep INT,	
	PRIMARY KEY(idCliente),
)	

CREATE TABLE VEICULO(
	
	idVeiculo SERIAL,
	ano DATE,
	placa VARCHAR(8),
	chassis VARCHAR(20),
	cor VARCHAR(30),
	idModelo INT NOT NULL,
	idCliente INT NOT NULL,
	PRIMARY KEY(idVeiculo),
	FOREIGN KEY(idModelo) REFERENCES MODELO(idModelo),
	FOREIGN KEY(idCliente) REFERENCES CLIENTE(idCliente),
)	

CREATE TABLE PRODUTO(
	idProduto SERIAL,
	descricao VARCHAR(255),
	valor FLOAT,
	qtd INT,
	qdtMinimo INT,
	idMarca INT NOT NULL,
	PRIMARY KEY(idProduto),
	FOREIGN KEY(idMarca) REFERENCES MARCA(idMarca)
)

CREATE TABLE SERVICO(
	idServico SERIAL,
	descricao VARCHAR(255),
	valor FLOAT,
	PRIMARY KEY(idServico)
);



