CREATE TABLE IF NOT EXISTS Marca (
  idMarca SERIAL,
  descricao VARCHAR(45) NULL,
  PRIMARY KEY (idMarca));

CREATE TABLE IF NOT EXISTS Modelo (
  idModelo SERIAL,
  descricao VARCHAR(45) NULL,
  idMarca INT NOT NULL,
  PRIMARY KEY (idModelo),
  FOREIGN KEY (idMarca) REFERENCES Marca (idMarca));


CREATE TABLE IF NOT EXISTS Contribuinte (
  idContribuinte SERIAL,
  dtNasc DATE NULL,
  telf VARCHAR(45) NULL,
  celular VARCHAR(45) NULL,
  logradouro VARCHAR(45) NULL,
  numero VARCHAR(45) NULL,
  bairro VARCHAR(45) NULL,
  cidade VARCHAR(45) NULL,
  estado VARCHAR(45) NULL,
  cep VARCHAR(45) NULL,
  PRIMARY KEY (idContribuinte));


CREATE TABLE IF NOT EXISTS Cliente (
  idCliente INT NOT NULL,
  rg VARCHAR(11) NULL,
  cpf VARCHAR(12) NULL,
  nome VARCHAR(45) NULL,
  PRIMARY KEY (idCliente),
  FOREIGN KEY (idCliente) REFERENCES Contribuinte (idContribuinte));

  
CREATE TABLE IF NOT EXISTS Veiculo (
  idVeiculo SERIAL,
  ano VARCHAR(4) NULL,
  idModelo INT NOT NULL,
  placa VARCHAR(45) NULL,
  chassis VARCHAR(45) NULL,
  cor VARCHAR(45) NULL,
  idCliente INT NOT NULL,
  PRIMARY KEY (idVeiculo),
  FOREIGN KEY (IdModelo) REFERENCES Modelo (idModelo),
  FOREIGN KEY (IdCliente) REFERENCES Cliente (idCliente));


CREATE TABLE IF NOT EXISTS Produto (
  idProduto SERIAL,
  descricao VARCHAR(45) NULL,
  idMarca INT NOT NULL,
  valor DECIMAL(2) NULL,
  qtd INT NULL,
  qtdMinimo INT NULL,
  PRIMARY KEY (idProduto),
  FOREIGN KEY (idMarca) REFERENCES Marca (idMarca));


CREATE TABLE IF NOT EXISTS Servico (
  idServico SERIAL,
  descricao VARCHAR(45) NULL,
  valor DECIMAL(4) NULL,
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
  login VARCHAR(45) NULL,
  senha VARCHAR(45) NULL,
  PRIMARY KEY (idFucionario),
  FOREIGN KEY (idFucionario) REFERENCES Cliente (idCliente));

CREATE TABLE IF NOT EXISTS Nota (
  idNota SERIAL,
  Datas DATE NULL,
  idFuncionario INT NOT NULL,
  idVeiculo INT NULL,
  numeroDaNota INT NULL,
  tipoNota VARCHAR(45) NULL,
  idContribuinte INT NOT NULL,
  descricao VARCHAR(254) NULL,
  PRIMARY KEY (idNota),
  FOREIGN KEY (idFuncionario) REFERENCES Funcionario (idFucionario),
  FOREIGN KEY (idVeiculo) REFERENCES Veiculo (idVeiculo),
  FOREIGN KEY (idContribuinte) REFERENCES Contribuinte (idContribuinte));

CREATE TABLE IF NOT EXISTS ListaDeServicos (
  idNota INT NOT NULL,
  idServico INT NOT NULL,
  PRIMARY KEY (idNota, idServico),
  FOREIGN KEY (idServico) REFERENCES Servico (idServico) ,
  FOREIGN KEY (idNota) REFERENCES Nota (idNota));


CREATE TABLE IF NOT EXISTS ListaDeProdutos (
  idProduto INT NOT NULL,
  idNota INT NOT NULL,
  qnt INT NOT NULL,
  PRIMARY KEY (idProduto, idNota),
  FOREIGN KEY (idProduto) REFERENCES Produto (idProduto), 
  FOREIGN KEY (idNota) REFERENCES Nota (idNota));
  
 
  
