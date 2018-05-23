CREATE TABLE IF NOT EXISTS Marca (
  idMarca SERIAL,
  Descricao VARCHAR(45) NULL,
  PRIMARY KEY (idMarca));

CREATE TABLE IF NOT EXISTS Modelo (
  idModelo SERIAL,
  Descricao VARCHAR(45) NULL,
  idMarca INT NOT NULL,
  PRIMARY KEY (idModelo),
  FOREIGN KEY (idMarca) REFERENCES Marca (idMarca));


CREATE TABLE IF NOT EXISTS Contribuinte (
  idContribuinte SERIAL,
  Nome_Razao VARCHAR(45) NULL,
  CPF_CNPJ VARCHAR(45) NULL,
  DataDeNascimento DATE NULL,
  Telefone VARCHAR(45) NULL,
  Celular VARCHAR(45) NULL,
  Logradouro VARCHAR(45) NULL,
  Número VARCHAR(45) NULL,
  Bairro VARCHAR(45) NULL,
  Cidade VARCHAR(45) NULL,
  Estado VARCHAR(45) NULL,
  CEP VARCHAR(45) NULL,
  PRIMARY KEY (idContribuinte));


CREATE TABLE IF NOT EXISTS Pessoa (
  idPessoa INT NOT NULL,
  RG VARCHAR(11) NULL,
  CPF VARCHAR(12) NULL,
  nome VARCHAR(45) NULL,
  PRIMARY KEY (idPessoa),
  FOREIGN KEY (idPessoa) REFERENCES Contribuinte (idContribuinte));


CREATE TABLE IF NOT EXISTS Veiculo (
  idVeiculo SERIAL,
  Ano DATE NULL,
  IdModelo INT NOT NULL,
  Placa VARCHAR(45) NULL,
  Chassis VARCHAR(45) NULL,
  Cor VARCHAR(45) NULL,
  IdCliente INT NOT NULL,
  PRIMARY KEY (idVeiculo),
  FOREIGN KEY (IdModelo) REFERENCES Modelo (idModelo),
  FOREIGN KEY (IdCliente) REFERENCES Pessoa (idPessoa));



CREATE TABLE IF NOT EXISTS Produto (
  idProduto SERIAL,
  Descricao VARCHAR(45) NULL,
  idMarca INT NOT NULL,
  Valor DECIMAL(2) NULL,
  Quantidade INT NULL,
  QuantidadeMinima INT NULL,
  PRIMARY KEY (idProduto),
  FOREIGN KEY (idMarca) REFERENCES Marca (idMarca));


CREATE TABLE IF NOT EXISTS Servico (
  idServico SERIAL,
  Descricao VARCHAR(45) NULL,
  Valor DECIMAL(2) NULL,
  PRIMARY KEY (idServico));
  

CREATE TABLE IF NOT EXISTS Fornecedor (
  idFornecedor INT NOT NULL,
  NomeFantazia VARCHAR(45) NULL,
  CNPJ VARCHAR(15) NULL,
  razaoSocial VARCHAR(45) NULL,
  PRIMARY KEY (idFornecedor),
  FOREIGN KEY (idFornecedor) REFERENCES Contribuinte (idContribuinte));


CREATE TABLE IF NOT EXISTS Funcionario (
  idFucionario INT NOT NULL,
  Login VARCHAR(45) NULL,
  Senha VARCHAR(45) NULL,
  PRIMARY KEY (idFucionario),
  FOREIGN KEY (idFucionario) REFERENCES Pessoa (idPessoa));


CREATE TABLE IF NOT EXISTS Nota (
  idNota SERIAL,
  Datas DATE NULL,
  idFuncionario INT NOT NULL,
  idVeiculo INT NULL,
  NumeroDaNota INT NULL,
  TipoNota VARCHAR(45) NULL,
  idCliente INT NOT NULL,
  Fornecedor INT NOT NULL,
  Descricao VARCHAR(254) NULL,
  PRIMARY KEY (idNota),
  FOREIGN KEY (idFuncionario) REFERENCES Funcionario (idFucionario),
  FOREIGN KEY (idVeiculo) REFERENCES Veiculo (idVeiculo),
  FOREIGN KEY (idCliente) REFERENCES Contribuinte (idContribuinte),
  FOREIGN KEY (Fornecedor) REFERENCES Contribuinte (idContribuinte));

CREATE TABLE IF NOT EXISTS ListaDeServicos (
  idNota INT NOT NULL,
  idServico INT NOT NULL,
  PRIMARY KEY (idNota, idServico),
  FOREIGN KEY (idServico) REFERENCES Servico (idServico) ,
  FOREIGN KEY (idNota) REFERENCES Nota (idNota));


CREATE TABLE IF NOT EXISTS ListaDeProdutos (
  idProduto INT NOT NULL,
  idNota INT NOT NULL,
  PRIMARY KEY (idProduto, idNota),
  FOREIGN KEY (idProduto) REFERENCES Produto (idProduto), 
  FOREIGN KEY (idNota) REFERENCES Nota (idNota));