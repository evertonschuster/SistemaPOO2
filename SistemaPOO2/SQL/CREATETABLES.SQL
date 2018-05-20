CREATE TABLE MARCA(
	idMarca INT,
	descricao VARCHAR(255),
	PRIMARY KEY(idMarca)
);

CREATE TABLE MODELO(
	
	idModelo INT,
	descricao VARCHAR(255),
	idMarca INT NOT NULL,
	PRIMARY KEY(idModelo),
	FOREIGN KEY(idMarca) REFERENCES MARCA(idMarca)
)	